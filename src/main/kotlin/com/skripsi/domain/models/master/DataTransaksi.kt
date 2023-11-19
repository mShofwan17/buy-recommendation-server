package com.skripsi.domain.models.master

import com.skripsi.domain.models.DataTransaksiBool
import kotlinx.serialization.Serializable

@Serializable
data class DataTransaksi(
    val kodeBarang: String,
    val namaBarang: String = "",
    val golongan: String = "",
    val stok: Int = 0,
    val isDiskon: Int = 0,
    val penjualan: Double = 0.0,
    val pembelian: Double = 0.0
) {
    fun toDataTransaksiBool(): DataTransaksiBool {
        return DataTransaksiBool(
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon == 0,
            penjualan = penjualan.toInt()
        )
    }
}
