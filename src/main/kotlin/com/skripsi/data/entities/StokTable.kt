package com.skripsi.data.entities

import org.jetbrains.exposed.sql.Table

object StokTable : Table("stok") {
    val kode = varchar("kode",255)
    val stok = integer("stok")
}