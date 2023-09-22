package com.skripsi.data.entities

import com.skripsi.domain.models.SalesPrediction
import kotlinx.serialization.Serializable

@Serializable
data class DataTraining(
    val nama: String = "Barang",
    val kategori: String,
    val persediaan: String,
    val isPromosi: Boolean,
    val isMemenuhiTarget: Boolean? = null,
    val salesPredictionDetail: SalesPrediction? = null,
    val prediksi: String? = null
)
