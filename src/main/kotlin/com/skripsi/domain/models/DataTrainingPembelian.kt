package com.skripsi.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DataTrainingPembelian(
    val kodeBarang: String = "",
    val namaBarang: String = "",
    val kategori: String = "",
    val stok: String = "", //Banyak, Standar, Sedikit
    val isDiskon: Boolean = false,
    val penjualan: String = "", //Banyak, Cukup, Sedikit
    val pembelian: Boolean = false //beli atau tidak
)
