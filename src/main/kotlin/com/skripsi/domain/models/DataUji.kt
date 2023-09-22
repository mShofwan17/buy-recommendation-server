package com.skripsi.domain.models

import com.skripsi.data.entities.DataTraining
import com.skripsi.utils.*
import kotlinx.serialization.Serializable

@Serializable
data class DataUji(
    val nama: String = "Barang",
    val harga: String,
    val kategori: String,
    val persediaan: String,
    val isPromosi: Boolean,
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

    fun getHarga(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.harga(
            harga = this.harga,
            isPositive = isPositive
        ).size.toDouble()
    }

    fun getPersediaan(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.persediaan(
            persediaan = this.persediaan,
            isPositive = isPositive
        ).size.toDouble()
    }

    fun getPromosi(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return items.promosi(
            isPromosi = this.isPromosi,
            isPositive = isPositive
        ).size.toDouble()
    }
}
