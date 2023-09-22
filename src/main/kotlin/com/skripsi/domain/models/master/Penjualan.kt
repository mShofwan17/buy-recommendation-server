package com.skripsi.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Penjualan(
    val id: Int = 0,
    val kodeBarang: String,
    val namaBarang: String,
    val qty: Double,
    val harga: Double,
)
