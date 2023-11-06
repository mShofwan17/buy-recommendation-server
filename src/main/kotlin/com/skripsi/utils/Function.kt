package com.skripsi.utils


fun labeledStok(stok: Int): String {
    return when {
        stok <= 30 -> "Sedikit"
        stok in 31..100 -> "Standar"
        else -> "Banyak"
    }
}

fun labeledDiskon(diskon: Short): Boolean{
    return diskon.toInt() == 1
}
fun labeledPenjualan(penjualan: Int): String {
    return when {
        penjualan <= 50 -> "Sedikit"
        penjualan in 51..100 -> "Cukup"
        else -> "Banyak"
    }
}

fun classPembelian(pembelian: Int): Boolean {
    return pembelian > 0
}