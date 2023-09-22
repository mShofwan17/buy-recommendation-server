package com.skripsi.data.entities

import org.jetbrains.exposed.sql.Table

object ItemPenjualan : Table("itempenjualan") {
    val kodeBarang = varchar("kode_barang",255)
    val namaBarang = varchar("nama_barang",255)
    val qty = double("qty")
    val harga = double("harga")
}