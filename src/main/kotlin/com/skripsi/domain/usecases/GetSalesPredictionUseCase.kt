package com.skripsi.domain.usecases

import com.skripsi.domain.models.BuyRecommendation
import com.skripsi.domain.models.DataTrainingPembelian
import com.skripsi.domain.models.DataUjiPembelian
import com.skripsi.utils.NaiveBayesUtil
import com.skripsi.utils.recommendation

class GetSalesPredictionUseCase {
    operator fun invoke(
        dataTrainingPembelian: List<DataTrainingPembelian>,
        dataUji: DataUjiPembelian
    ): BuyRecommendation {
        NaiveBayesUtil.apply {
            val positive = calculatePositive(dataUji, dataTrainingPembelian)
            val negative = calculateNegative(dataUji, dataTrainingPembelian)
            val result = resultNaiveBayes(positive,negative)
            return BuyRecommendation(
                positive,
                negative,
                result,
                rekomendasi = result.recommendation()
            )
        }
    }
}