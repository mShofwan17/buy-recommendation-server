package com.skripsi.domain.usecases.master

import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.domain.models.master.Pembelian
import com.skripsi.domain.models.master.Penjualan

class GetListPembelianUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): List<Pembelian> {
        return repository.getPembelian()
            .mapIndexed { index, pembelian ->
                pembelian.copy(id = index + 1)
            }
    }
}