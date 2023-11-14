package com.skripsi.data.entities

import org.jetbrains.exposed.sql.Table

object ItemBarangTable : Table("barang") {
    val kode = varchar("kode",255)
    val nama = varchar("nama",255)
    val kategori = varchar("kategori",255)
}