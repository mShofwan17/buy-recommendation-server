package com.skripsi.utils

import com.skripsi.data.entities.DataTraining
import com.skripsi.domain.models.DataTrainingPembelian

fun String.firstUpper(): String = replaceFirstChar {
    it.uppercase()
}

fun Boolean.recommendation(): String {
    return if (this) "Rekomendasi Beli"
    else "Rekomendasi Tidak Beli"
}
/*
fun List<DataTraining>.allTrue(): List<DataTraining> =
    filter { it.isMemenuhiTarget == true }
fun List<DataTraining>.allFalse(): List<DataTraining> =
    filter { it.isMemenuhiTarget == false }
fun List<DataTraining>.allTrueCount(): Int = allTrue().size
fun List<DataTraining>.allFalseCount(): Int = allFalse().size
fun List<DataTraining>.kategori(isPositive: Boolean, kategori: String): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == kategori.firstUpper() }
fun List<DataTraining>.harga(isPositive: Boolean, harga: String): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.harga == harga.firstUpper() }
fun List<DataTraining>.persediaan(isPositive: Boolean, persediaan: String): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.persediaan == persediaan.firstUpper() }
fun List<DataTraining>.promosi(isPositive: Boolean, isPromosi: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.isPromosi == isPromosi}
*/


//pembelian
fun List<DataTrainingPembelian>.allTrue(): List<DataTrainingPembelian> =
    filter { it.pembelian }
fun List<DataTrainingPembelian>.allFalse(): List<DataTrainingPembelian> =
    filter { !it.pembelian }
fun List<DataTrainingPembelian>.allTrueCount(): Int = allTrue().size
fun List<DataTrainingPembelian>.allFalseCount(): Int = allFalse().size
fun List<DataTrainingPembelian>.kategori(isPositive: Boolean, kategori: String): List<DataTrainingPembelian> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == kategori.firstUpper() }
fun List<DataTrainingPembelian>.stok(isPositive: Boolean, stok: String): List<DataTrainingPembelian> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.stok == stok.firstUpper() }
fun List<DataTrainingPembelian>.diskon(isPositive: Boolean, isDiskon: Boolean): List<DataTrainingPembelian> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.isDiskon == isDiskon }
fun List<DataTrainingPembelian>.penjualan(isPositive: Boolean, penjualan: String): List<DataTrainingPembelian> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.penjualan == penjualan.firstUpper() }