package com.skripsi.utils

import com.skripsi.domain.models.DataTraining
import com.skripsi.domain.models.DataUji
import java.math.BigDecimal

object NaiveBayesUtil {
    fun calculatePositive(
        dataUji: DataUji,
        dataTrainings: List<DataTraining>,
    ): BigDecimal {
        dataTrainings.apply {
            val positive = allTrueCount().toDouble()

            val kategori = (dataUji.getKategori(allTrue()) / positive).decimalFormat()
            val persediaan = (dataUji.getStok(allTrue()) / positive).decimalFormat()
            val promosi = (dataUji.getDiskon(allTrue()) / positive).decimalFormat()
            val penjualan = (dataUji.getPenjualan(allTrue()) / positive).decimalFormat()
            val positiveSize = (positive /size.toDouble()).decimalFormat()

            val result = kategori * persediaan * promosi * penjualan * positiveSize

            println("$kategori * $persediaan * $promosi * $penjualan * $positiveSize")

            println("Positive : $positive / $size")
            println("${dataUji.namaBarang} Kategori Positive : $kategori")
            println("${dataUji.namaBarang} persediaan Positive : $persediaan")
            println("${dataUji.namaBarang} promosi Positive : $promosi")
            println("${dataUji.namaBarang} penjualan Positive : $penjualan")
            println("${dataUji.namaBarang} Result Positive : $result")

            return result.toBigDecimal().decimalFormat()
            //return result
        }
    }

    fun calculateNegative(
        dataUji: DataUji,
        dataTrainings: List<DataTraining>,
    ): BigDecimal {
        dataTrainings.apply {
            val negative = allFalseCount().toDouble()

            val kategori = (dataUji.getKategori(allFalse()) / negative).decimalFormat()
            val persediaan = (dataUji.getStok(allFalse()) / negative).decimalFormat()
            val promosi = (dataUji.getDiskon(allFalse()) / negative).decimalFormat()
            val penjualan = (dataUji.getPenjualan(allFalse()) / negative).decimalFormat()
            val negativeSize = (negative /size.toDouble()).decimalFormat()

            val result = kategori * persediaan * promosi * penjualan * negativeSize
            println("----------------------------------------------------------------")
            println("dataUji : $dataUji")
            println("Negative : $negative / $size")
            println("${dataUji.namaBarang} Kategori Negative : $kategori")
            println("${dataUji.namaBarang} persediaan Negative : $persediaan")
            println("${dataUji.namaBarang} promosi Negative : $promosi")
            println("${dataUji.namaBarang} penjualan Negative : $penjualan")
            println("${dataUji.namaBarang} Negative Size : $negativeSize")
            return result.toBigDecimal().decimalFormat()
           // return result
        }
    }

    fun resultNaiveBayes(
        positive: BigDecimal,
        negative: BigDecimal
    ): Boolean {
        return positive >= negative
    }
}