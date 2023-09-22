package com.skripsi.di

import com.skripsi.data.data_source.DataSource
import com.skripsi.data.repositories.data_training.DataTrainingRepository
import com.skripsi.data.repositories.data_training.DataTrainingRepositoryImpl
import com.skripsi.domain.usecases.GetListDataTrainingUseCase
import com.skripsi.domain.usecases.GetSalesPredictionUseCase
import org.koin.dsl.module

val databaseModule = module {
    single<DataSource> { DataSource }
}

val repositoryModule = module {
    single<DataTrainingRepository> { DataTrainingRepositoryImpl(dataSource = get()) }
}

val useCaseModule = module {
    single { GetListDataTrainingUseCase(get()) }
    single { GetSalesPredictionUseCase(get()) }
}