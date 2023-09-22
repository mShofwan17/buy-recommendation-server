package com.skripsi.data.repositories.data_master

import com.skripsi.domain.models.master.Golongan
import com.skripsi.domain.models.master.Kategori
import com.skripsi.domain.models.master.Pembelian
import com.skripsi.domain.models.master.Penjualan

interface DataMasterRepository {
    suspend fun getPenjualan() : List<Penjualan>
    suspend fun getPembelian() : List<Pembelian>
    suspend fun getKategori(): List<Kategori>
    suspend fun getGolongan(): List<Golongan>
}