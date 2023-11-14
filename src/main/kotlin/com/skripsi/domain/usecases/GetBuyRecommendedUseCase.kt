package com.skripsi.domain.usecases

import com.skripsi.domain.models.BuyRecommendation
import com.skripsi.domain.models.DataTraining
import com.skripsi.domain.models.DataUji
import com.skripsi.utils.NaiveBayesUtil
import com.skripsi.utils.recommendation

class GetBuyRecommendedUseCase {
    operator fun invoke(
        dataTraining: List<DataTraining>,
        dataUji: DataUji
    ): BuyRecommendation {
        NaiveBayesUtil.apply {
            val positive = calculatePositive(dataUji, dataTraining)
            val negative = calculateNegative(dataUji, dataTraining)
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