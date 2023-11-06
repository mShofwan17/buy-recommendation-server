package com.skripsi.utils

import com.skripsi.domain.models.DataTrainingPembelian
import com.skripsi.domain.models.DataUjiPembelian
import java.text.DecimalFormat

object NaiveBayesUtil {
    private val df = DecimalFormat("#.####")

    fun calculatePositive(
        dataUji: DataUjiPembelian,
        dataTrainings: List<DataTrainingPembelian>,
    ): Double {
        dataTrainings.apply {
            val positive = allTrueCount().toDouble()

            val kategori = dataUji.getKategori(dataTrainings, true) / positive
            val persediaan = dataUji.getStok(dataTrainings, true) / positive
            val promosi = dataUji.getDiskon(dataTrainings, true) / positive
            val penjualan = dataUji.getPenjualan(dataTrainings, true) / positive

            val result = kategori * persediaan * promosi * penjualan * positive / size
            return df.format(result).toDouble()
            //return positive
        }
    }

    fun calculateNegative(
        dataUji: DataUjiPembelian,
        dataTrainings: List<DataTrainingPembelian>,
    ): Double {
        dataTrainings.apply {
            val negative = allFalseCount().toDouble()

            val kategori = dataUji.getKategori(dataTrainings, true) / negative
            val persediaan = dataUji.getStok(dataTrainings, false) / negative
            val promosi = dataUji.getDiskon(dataTrainings, false) / negative
            val penjualan = dataUji.getPenjualan(dataTrainings, false) / negative

            val result = kategori * persediaan * promosi * penjualan * negative / size
            return df.format(result).toDouble()
            //return negative
        }
    }

    fun resultNaiveBayes(
        positive: Double,
        negative: Double
    ): Boolean {
        val normalPositive = positive / positive + negative
        val normalNegative = negative / positive + negative

        return positive >= negative
    }
}