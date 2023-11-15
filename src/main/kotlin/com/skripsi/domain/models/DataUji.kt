package com.skripsi.domain.models

import com.skripsi.utils.*
import kotlinx.serialization.Serializable

@Serializable
data class DataUji(
    val kodeBarang: String = "",
    val namaBarang: String = "",
    val kategori: String = "",
    val stok: String = "", //Banyak, Standar, Sedikit
    val isDiskon: Boolean = false,
    val penjualan: String = "", //Banyak, Cukup, Sedikit
) {
    fun getKategori(
        items: List<DataTraining>
    ): Double {
        return items.kategori(
            kategori = this.kategori
        ).count().toDouble().decimalFormat()
    }

    fun getStok(
        items: List<DataTraining>
    ): Double {
        return items.stok(
            stok = this.stok
        ).size.toDouble().decimalFormat()
    }

    fun getDiskon(
        items: List<DataTraining>
    ): Double {
        return items.diskon(
            isDiskon = this.isDiskon
        ).size.toDouble().decimalFormat()
    }

    fun getPenjualan(
        items: List<DataTraining>
    ): Double {
        return items.penjualan(
            penjualan = this.penjualan
        ).size.toDouble().decimalFormat()
    }
}
