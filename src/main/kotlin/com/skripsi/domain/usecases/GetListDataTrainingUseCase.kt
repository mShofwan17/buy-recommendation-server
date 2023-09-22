package com.skripsi.domain.usecases

import com.skripsi.data.entities.DataTraining
import com.skripsi.data.repositories.data_training.DataTrainingRepository

class GetListDataTrainingUseCase(
    private val repository: DataTrainingRepository
) {
    suspend operator fun invoke(): List<DataTraining>{
        return repository.getDataTraining()
    }
}