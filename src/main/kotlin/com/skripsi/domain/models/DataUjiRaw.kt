package com.skripsi.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DataUjiRaw(
    val kodeBarang: String = "",
    val namaBarang: String = "",
    val kategori: String = "",
    val stok: Int = 0, //Banyak, Standar, Sedikit
    val isDiskon: Boolean = false,
    val penjualan: Int = 0, //Banyak, Cukup, Sedikit
)
