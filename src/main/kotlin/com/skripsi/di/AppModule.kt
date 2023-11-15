package com.skripsi.di

import com.skripsi.data.data_source.DatabaseFactory
import com.skripsi.data.repositories.data_master.DataMasterRepository
import com.skripsi.data.repositories.data_master.DataMasterRepositoryImpl
import com.skripsi.domain.usecases.GetListDataTransaksiUseCase
import com.skripsi.domain.usecases.GetListDataTrainingUseCase
import com.skripsi.domain.usecases.CalculateBuyRecommendedUseCase
import com.skripsi.domain.usecases.master.*
import org.koin.dsl.module

val databaseModule = module {
    single<DatabaseFactory?> { DatabaseFactory() }
}

val repositoryModule = module {
    single<DataMasterRepository> { DataMasterRepositoryImpl(db = get()) }
}

val useCaseModule = module {
    single { CalculateBuyRecommendedUseCase() }

    single { GetListPembelianUseCase(get()) }
    single { GetListPenjualanUseCase(get()) }
    single { GetListBarangUseCase(get()) }
    single { GetListDataTransaksiUseCase(get()) }
    single { GetListDataTrainingUseCase() }
}