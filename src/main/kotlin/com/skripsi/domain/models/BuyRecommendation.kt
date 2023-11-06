package com.skripsi.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class BuyRecommendation(
    val positiveResult: Double,
    val negativeResult: Double,
    val result: Boolean,
    val rekomendasi: String
)
