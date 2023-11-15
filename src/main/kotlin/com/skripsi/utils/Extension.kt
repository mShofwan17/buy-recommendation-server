package com.skripsi.utils

import com.skripsi.domain.models.master.DataTransaksi
import com.skripsi.domain.models.master.Pembelian
import com.skripsi.domain.models.master.Penjualan
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import java.math.BigDecimal
import java.text.DecimalFormat

fun MutableCollection<DataTransaksi>.getList(): List<DataTransaksi>{
    return this.toList().filter {
        it.stok != 0 && it.penjualan<it.stok
    }.take(1000)
}

fun Query.asPenjualan(
    onPenjualan: (ResultRow) -> Penjualan
): List<Penjualan> {
      return this.map {
           onPenjualan(it)
     }
}

fun Query.asPembelian(
    onPembelian: (ResultRow) -> Pembelian
): List<Pembelian> {
    return this.limit(9800).map {
        onPembelian(it)
    }
}

fun Double.decimalFormat() : Double{
    val df = DecimalFormat("#")
    df.maximumFractionDigits = 4

    return df.format(this).toDouble()
}

fun BigDecimal.decimalFormat() : BigDecimal{
    val df = DecimalFormat("#")
    df.maximumFractionDigits = 5

    return df.format(this).toBigDecimal()
}