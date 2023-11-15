package com.skripsi.data.repositories.data_master

import com.skripsi.domain.models.master.*
import org.jetbrains.exposed.sql.Database

interface DataMasterRepository {
    val dbInstance : Database?
    suspend fun getPenjualan() : List<Penjualan>
    suspend fun getPembelian() : List<Pembelian>
    suspend fun getBarang(): List<Barang>
    suspend fun getStok(): List<Stok>
    suspend fun getBarangDiskon(): List<BarangDiskon>
    suspend fun getDataTransaksi(): List<DataTransaksi>
}