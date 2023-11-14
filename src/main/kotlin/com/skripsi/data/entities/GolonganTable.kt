package com.skripsi.data.entities

import org.jetbrains.exposed.sql.Table

object GolonganTable : Table("golongan") {
    val kode = varchar("kode",255)
    val kategori = varchar("kategori",255)
}