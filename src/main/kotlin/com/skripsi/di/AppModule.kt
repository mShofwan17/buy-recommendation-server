package com.skripsi.di

import com.skripsi.data.data_source.DataSource
import com.skripsi.data.data_source.DatabaseFactory
import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.data.repositories.data_master.DataMasterRepositoryImpl
import com.skripsi.data.repositories.data_training.DataTrainingRepository
import com.skripsi.data.repositories.data_training.DataTrainingRepositoryImpl
import com.skripsi.domain.usecases.GetListDataTrainingUseCase
import com.skripsi.domain.usecases.master.GetListPenjualanUseCase
import com.skripsi.domain.usecases.GetSalesPredictionUseCase
import com.skripsi.domain.usecases.master.GetListGolonganUseCase
import com.skripsi.domain.usecases.master.GetListKategoriUseCase
import com.skripsi.domain.usecases.master.GetListPembelianUseCase
import org.koin.dsl.module

val databaseModule = module {
    single<DataSource> { DataSource }
    single<DatabaseFactory?> { DatabaseFactory() }
}

val repositoryModule = module {
    single<DataTrainingRepository> { DataTrainingRepositoryImpl(dataSource = get()) }
    single<DataMasterRepository> { DataMasterRepositoryImpl(db = get()) }
}

val useCaseModule = module {
    single { GetListDataTrainingUseCase(get()) }
    single { GetSalesPredictionUseCase(get()) }

    single { GetListPembelianUseCase(get()) }
    single { GetListPenjualanUseCase(get()) }
    single { GetListKategoriUseCase(get()) }
    single { GetListGolonganUseCase(get()) }
}