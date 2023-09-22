package com.skripsi.utils

import com.skripsi.data.entities.DataTraining

fun String.firstUpper(): String = replaceFirstChar {
    it.uppercase()
}
fun Boolean.hasilPrediksi() : String{
    return if (this) "Memenuhi Target"
    else "Tidak Memenuhi Target"
}
fun List<DataTraining>.allTrue(): List<DataTraining> =
    filter { it.isMemenuhiTarget == true }

fun List<DataTraining>.allFalse(): List<DataTraining> =
    filter { it.isMemenuhiTarget == false }

fun List<DataTraining>.allTrueCount(): Int = allTrue().size
fun List<DataTraining>.allFalseCount(): Int = allFalse().size

fun List<DataTraining>.kategoriMakanan(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == "makanan".firstUpper() }

fun List<DataTraining>.kategoriMinuman(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == "minuman".firstUpper() }

fun List<DataTraining>.kategoriKebutuhan(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == "kebutuhan".firstUpper() }

fun List<DataTraining>.kategoriKosmetik(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.kategori == "kosmetik".firstUpper() }

fun List<DataTraining>.persediaanBanyak(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.persediaan == "banyak".firstUpper() }

fun List<DataTraining>.persediaanSedikit(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.persediaan == "sedikit".firstUpper() }

fun List<DataTraining>.promosiTrue(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { it.isPromosi }

fun List<DataTraining>.promosiFalse(isPositive: Boolean): List<DataTraining> =
    (if (isPositive) allTrue() else allFalse())
        .filter { !it.isPromosi }