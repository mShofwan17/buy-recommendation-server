package com.skripsi.domain.models

import com.skripsi.utils.diskon
import com.skripsi.utils.kategori
import com.skripsi.utils.penjualan
import com.skripsi.utils.stok
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
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.kategori(
            kategori = this.kategori,
            isPositive = isPositive
        ).size.toDouble()
    }

    fun getStok(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.stok(
            stok = this.stok,
            isPositive = isPositive
        ).size.toDouble()
    }

    fun getDiskon(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.diskon(
            isDiskon = this.isDiskon,
            isPositive = isPositive
        ).size.toDouble()
    }

    fun getPenjualan(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.penjualan(
            penjualan = this.penjualan,
            isPositive = isPositive
        ).size.toDouble()
    }
}
