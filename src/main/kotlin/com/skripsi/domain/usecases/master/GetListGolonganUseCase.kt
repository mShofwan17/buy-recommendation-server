package com.skripsi.domain.usecases.master

import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.domain.models.master.Penjualan

class GetListKategoriUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): List<Penjualan> {
        return repository.getPenjualan()
            .mapIndexed { index, penjualan ->
                penjualan.copy(id = index + 1)
            }
    }
}