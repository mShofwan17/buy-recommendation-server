package com.skripsi.domain.models

import com.skripsi.data.entities.DataTraining
import com.skripsi.utils.*
import kotlinx.serialization.Serializable

@Serializable
data class DataUji(
    val nama: String = "Barang",
    val kategori: String,
    val persediaan: String,
    val isPromosi: Boolean,
) {
    fun getKategori(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return when (this.kategori.firstUpper()) {
            "Makanan" -> items.kategoriMakanan(isPositive).size
            "Minuman" -> items.kategoriMinuman(isPositive).size
            "Kebutuhan" -> items.kategoriKebutuhan(isPositive).size
            "Kosmetik" -> items.kategoriKosmetik(isPositive).size
            else -> 0
        }.toDouble()
    }

    fun getPersediaan(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return when (this.persediaan.firstUpper()) {
            "Banyak" -> items.persediaanBanyak(isPositive).size
            "Sedikit" -> items.persediaanSedikit(isPositive).size
            else -> 0
        }.toDouble()
    }

    fun getPromosi(
        items: List<DataTraining>,
        isPositive: Boolean
    ): Double {
        return when (this.isPromosi) {
            true -> items.promosiTrue(isPositive).size
            false -> items.promosiFalse(isPositive).size
        }.toDouble()
    }
}
