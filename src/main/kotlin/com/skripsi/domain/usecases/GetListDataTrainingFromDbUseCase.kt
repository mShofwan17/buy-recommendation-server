package com.skripsi.domain.usecases

import com.skripsi.data.entities.DataTraining
import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.data.repositories.data_training.DataTrainingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetListDataTrainingFromDbUseCase(
    private val repository: DataMasterRepository
) {
    suspend operator fun invoke(): Flow<List<DataTraining>>{
        return flow {
            val penjualan = repository.getPenjualan()
            emit(listOf())
        }
    }
}