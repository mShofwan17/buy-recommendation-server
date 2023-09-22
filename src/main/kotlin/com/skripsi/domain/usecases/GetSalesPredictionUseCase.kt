package com.skripsi.domain.usecases

import com.skripsi.data.repositories.data_training.DataTrainingRepository
import com.skripsi.domain.models.DataUji
import com.skripsi.domain.models.SalesPrediction
import com.skripsi.utils.NaiveBayesUtil

class GetSalesPredictionUseCase(
    private val repository: DataTrainingRepository
) {
    suspend operator fun invoke(
        dataUji: DataUji
    ): SalesPrediction {
        NaiveBayesUtil.apply {
            val dataTraining = repository.getDataTraining()
            val positive = calculatePositive(dataUji, dataTraining)
            val negative = calculateNegative(dataUji, dataTraining)

            return SalesPrediction(
                nama = dataUji.nama,
                positive,
                negative,
                resultNaiveBayes(positive,negative)
            )
        }
    }
}