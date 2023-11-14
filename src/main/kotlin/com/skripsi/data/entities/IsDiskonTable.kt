package com.skripsi.data.entities

import org.jetbrains.exposed.sql.Table

object IsDiskonTable : Table("barang_is_diskon") {
    val kode = varchar("kode",255)
    val isDiskon = short("is_diskon")
}