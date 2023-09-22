package com.skripsi.data.repositories.data_training

import com.skripsi.data.data_source.DataSource
import com.skripsi.data.entities.DataTraining

class DataTrainingRepositoryImpl(
    private val dataSource: DataSource
): DataTrainingRepository {
    override suspend fun getDataTraining(): List<DataTraining> {
        return dataSource.dataTrainingSample()
    }

}