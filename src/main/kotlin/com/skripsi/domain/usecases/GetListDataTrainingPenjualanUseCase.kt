package com.skripsi.domain.usecases

import com.skripsi.domain.models.DataTraining
import com.skripsi.domain.models.master.DataTransaksi
import com.skripsi.utils.classPembelian
import com.skripsi.utils.labeledDiskon
import com.skripsi.utils.labeledPenjualan
import com.skripsi.utils.labeledStok

class GetListDataTrainingPenjualanUseCase {
    operator fun invoke(
        dataTransaksi: List<DataTransaksi>
    ): List<DataTraining> {
        return dataTransaksi.map {
            DataTraining(
                kodeBarang = it.kodeBarang,
                namaBarang = it.namaBarang,
                kategori = it.golongan,
                isDiskon = labeledDiskon(it.isDiskon),
                stok = labeledStok(it.stok),
                penjualan = labeledPenjualan(it.penjualan.toInt()),
                pembelian = classPembelian(it.pembelian.toInt())
            )
        }
    }

}