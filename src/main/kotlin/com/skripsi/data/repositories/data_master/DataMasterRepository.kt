package com.skripsi.data.repositories.data_master

import com.skripsi.domain.models.master.*

interface DataMasterRepository {
    suspend fun getPenjualan() : List<Penjualan>
    suspend fun getPembelian() : List<Pembelian>
    suspend fun getKategori(): List<Kategori>
    suspend fun getGolongan(): List<Golongan>

    suspend fun getBarang(): List<Barang>
    suspend fun getDataMentah(): List<DataMentah>
    suspend fun getDataRunBlocking(): List<DataMentah>
}