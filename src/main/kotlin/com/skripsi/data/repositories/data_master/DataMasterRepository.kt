package com.skripsi.data.repositories.data_master

import com.skripsi.domain.models.Penjualan

interface DataMasterRepository {
    suspend fun getPenjualan() : List<Penjualan>
}