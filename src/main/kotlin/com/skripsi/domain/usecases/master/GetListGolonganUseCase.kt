package com.skripsi.domain.usecases.master

import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.domain.models.master.Golongan
import com.skripsi.domain.models.master.Penjualan

class GetListGolonganUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): List<Golongan> {
        return repository.getGolongan().distinctBy { it.name }
    }
}