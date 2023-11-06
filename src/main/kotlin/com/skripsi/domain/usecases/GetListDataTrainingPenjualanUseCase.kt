package com.skripsi.domain.usecases

import com.skripsi.domain.models.DataTrainingPembelian
import com.skripsi.domain.models.master.DataMentah
import com.skripsi.utils.classPembelian
import com.skripsi.utils.labeledDiskon
import com.skripsi.utils.labeledPenjualan
import com.skripsi.utils.labeledStok

class GetListDataTrainingPenjualanUseCase {
    operator fun invoke(
        dataMentah: List<DataMentah>
    ): List<DataTrainingPembelian> {
        return dataMentah.map {
            DataTrainingPembelian(
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