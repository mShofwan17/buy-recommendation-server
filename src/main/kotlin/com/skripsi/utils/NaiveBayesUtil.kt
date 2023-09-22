package com.skripsi.utils

import com.skripsi.data.entities.DataTraining
import com.skripsi.domain.models.DataUji
import java.text.DecimalFormat

object NaiveBayesUtil {
    val df = DecimalFormat("#.####")

    fun calculatePositive(
        dataUji: DataUji,
        dataTrainings: List<DataTraining>,
    ): Double {
        dataTrainings.apply {
            val positive = allTrueCount().toDouble()

            val kategori = dataUji.getKategori(dataTrainings, true) / positive
            val persediaan = dataUji.getPersediaan(dataTrainings, true) / positive
            val promosi = dataUji.getPromosi(dataTrainings, true) / positive

            val result = kategori * persediaan * promosi * positive/size
            return df.format(result).toDouble()
        }
    }

    fun calculateNegative(
        dataUji: DataUji,
        dataTrainings: List<DataTraining>,
    ): Double {
        dataTrainings.apply {
            val negatives = allFalseCount().toDouble()

            val kategori = dataUji.getKategori(dataTrainings, false) / negatives
            val persediaan = dataUji.getPersediaan(dataTrainings, false) / negatives
            val promosi = dataUji.getPromosi(dataTrainings, false) / negatives

            val result = kategori * persediaan * promosi * negatives/size
            return df.format(result).toDouble()
        }
    }

    fun resultNaiveBayes(
        positive: Double,
        negative: Double
    ): Boolean {
       /* val normalPositive = positive/positive+negative
        val normalNegative = negative/positive+negative*/

        return positive > negative
    }
}