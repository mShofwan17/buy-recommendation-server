package com.skripsi.domain.models

import com.skripsi.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class BuyRecommendation(
    @Serializable(with = BigDecimalSerializer::class)
    val positiveResult: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val negativeResult: BigDecimal,
    val result: Boolean,
    val rekomendasi: String
)
