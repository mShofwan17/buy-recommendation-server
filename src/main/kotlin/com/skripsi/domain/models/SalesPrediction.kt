package com.skripsi.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class SalesPrediction(
    val nama: String = "Barang",
    val positiveResult: Double,
    val negativeResult: Double,
    val result: Boolean,
)
