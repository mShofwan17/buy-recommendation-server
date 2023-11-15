package com.skripsi.domain.usecases

import com.skripsi.domain.models.DataTraining
import com.skripsi.domain.models.master.DataTransaksi
import com.skripsi.utils.classPembelian
import com.skripsi.utils.labeledDiskon
import com.skripsi.utils.labeledPenjualan
import com.skripsi.utils.labeledStok

class GetListDataTrainingUseCase {
    operator fun invoke(
        dataTransaksi: List<DataTransaksi>
    ): List<DataTraining> {
        return dataTransaksi.map {
            DataTraining(
                kodeBarang = it.kodeBarang,
                namaBarang = it.namaBarang,
                kategori = it.golongan,
                isDiskon = it.isDiskon.labeledDiskon(),
                stok = it.stok.labeledStok(),
                penjualan = it.penjualan.toInt().labeledPenjualan(),
                pembelian = it.pembelian.toInt().classPembelian()
            )
        }
    }

}