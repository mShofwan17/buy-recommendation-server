package com.skripsi.domain.usecases.master

import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.domain.models.master.Barang
import com.skripsi.domain.models.master.Golongan
import com.skripsi.domain.models.master.Penjualan

class GetListBarangUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): List<Barang> {
        return repository.getBarang().distinctBy { it.kodeBarang }
    }
}