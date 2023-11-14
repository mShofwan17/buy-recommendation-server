package com.skripsi.utils

import com.skripsi.domain.models.DataTraining

fun String.firstUpper(): String = replaceFirstChar {
    it.uppercase()
}
fun Boolean.recommendation(): String {
    return if (this) "Rekomendasi Beli"
    else "Rekomendasi Tidak Beli"
}
fun List<DataTraining>.allTrue(): List<DataTraining> =
    filter { it.pembelian }
fun List<DataTraining>.allFalse(): List<DataTraining> =
    filter { !it.pembelian }
fun List<DataTraining>.allTrueCount(): Int = allTrue().size
fun List<DataTraining>.allFalseCount(): Int = allFalse().size
fun List<DataTraining>.kategori(isPositive: Boolean, kategori: String): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == kategori.firstUpper() }
fun List<DataTraining>.stok(isPositive: Boolean, stok: String): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.stok == stok.firstUpper() }
fun List<DataTraining>.diskon(isPositive: Boolean, isDiskon: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.isDiskon == isDiskon }
fun List<DataTraining>.penjualan(isPositive: Boolean, penjualan: String): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.penjualan == penjualan.firstUpper() }