package com.skripsi.domain.usecases.master

import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.domain.models.master.Kategori
import com.skripsi.domain.models.master.Penjualan

class GetListKategoriUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): List<Kategori> {
        return repository.getKategori().distinctBy { it.name }
    }
}