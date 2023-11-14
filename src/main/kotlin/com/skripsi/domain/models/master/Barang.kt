package com.skripsi.domain.models.master

import kotlinx.serialization.Serializable

@Serializable
data class Barang(
    val kodeBarang: String,
    val name: String
)