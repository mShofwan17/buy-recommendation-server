package com.skripsi.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DataTransaksiBool(
    val kodeBarang: String,
    val namaBarang: String = "",
    val golongan: String = "",
    val stok: Int = 0,
    val isDiskon: Boolean,
    val penjualan: Int = 0
)
