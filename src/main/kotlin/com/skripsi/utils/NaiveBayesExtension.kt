package com.skripsi.utils

import com.skripsi.data.entities.DataTraining

fun String.firstUpper(): String = replaceFirstChar {
    it.uppercase()
}

fun Boolean.hasilPrediksi(): String {
    return if (this) "Memenuhi Target"
    else "Tidak Memenuhi Target"
}

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
