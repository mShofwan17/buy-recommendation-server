package com.skripsi.utils

import com.skripsi.domain.models.master.DataTransaksi
import com.skripsi.domain.models.master.Pembelian
import com.skripsi.domain.models.master.Penjualan
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow

fun MutableCollection<DataTransaksi>.getList(): List<DataTransaksi>{
    return this.toList().filter {
        it.stok != 0 && it.penjualan<it.stok && it.namaBarang != ""
    }.take(1200).sortedBy { it.namaBarang }
}

fun Query.asPenjualan(
    onPenjualan: (ResultRow) -> Penjualan
): List<Penjualan> {
      return this.limit(10000).map {
           onPenjualan(it)
     }
}

fun Query.asPembelian(
    onPembelian: (ResultRow) -> Pembelian
): List<Pembelian> {
    return this.limit(9000).map {
        onPembelian(it)
    }
}