package com.skripsi.data.repositories.data_training

import com.skripsi.data.entities.DataTraining

interface DataTrainingRepository {
    suspend fun getDataTraining(): List<DataTraining>
}