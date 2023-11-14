package com.skripsi.domain.usecases

import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.domain.models.master.DataTransaksi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class GetListDataTransaksiUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): List<DataTransaksi> = runBlocking  {
        val penjualan = async { repository.getPenjualan().map {
            DataTransaksi(
                kodeBarang = it.kodeBarang,
                namaBarang = it.namaBarang,
                penjualan = it.qty,
            )
        } }
        val pembelian = async {
            repository.getPembelian().map {
                DataTransaksi(
                    kodeBarang = it.kodeBarang,
                    namaBarang = it.namaBarang,
                    pembelian = it.qty,
                )
            }
        }

        val result = async { repository.getDataRunBlocking() }

        return@runBlocking mergeData(result.await() + penjualan.await() + pembelian.await())
    }


    private fun mergeData(list: List<DataTransaksi>): List<DataTransaksi> {
        val mergedDataMap = mutableMapOf<String, DataTransaksi>()
        for (data in list) {
            val existingData = mergedDataMap[data.kodeBarang]
            if (existingData == null) {
                // Jika ID belum ada, tambahkan data baru
                mergedDataMap[data.kodeBarang] = data
            } else {
                // Jika ID sudah ada, gabungkan nilai penjualan dan pembelian
                val mergedData = DataTransaksi(
                    kodeBarang = data.kodeBarang,
                    namaBarang = data.namaBarang,
                    golongan = existingData.golongan,
                    isDiskon = existingData.isDiskon,
                    stok = existingData.stok,
                    penjualan = existingData.penjualan.plus(data.penjualan),
                    pembelian = existingData.pembelian.plus(data.pembelian)

                )
                mergedDataMap[data.kodeBarang] = mergedData
            }
        }

        return mergedDataMap.values.toList().take(1000)
    }
}