package com.skripsi.presentation.data_training

import com.skripsi.base.BasePresentation
import com.skripsi.domain.usecases.GetListDataTrainingUseCase
import com.skripsi.domain.usecases.GetSalesPredictionUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

object DataTrainingPresentation : BasePresentation() {
    private val getListDataTrainingUseCase: GetListDataTrainingUseCase
            by inject(GetListDataTrainingUseCase::class.java)
    private val getSalesPredictionUseCase: GetSalesPredictionUseCase
            by inject(GetSalesPredictionUseCase::class.java)


    fun getDataTraining(
        route: Route
    ) {
        route.get {
            responseResult { httpCode ->
                val response = getListDataTrainingUseCase.invoke()
                call.respond(
                    message = onSuccess(response),
                    status = httpCode
                )
            }
        }
    }

    fun getSalesPrediction(
        route: Route
    ) {/*
        route.post("/calculate") {
            responseResult { httpCode ->
                val dataUji = call.receive<DataUji>()
                val salesPrediction = getSalesPredictionUseCase(dataUji)

                val result = DataTraining(
                    nama = dataUji.nama,
                    kategori = dataUji.kategori,
                    harga = dataUji.harga,
                    persediaan = dataUji.persediaan,
                    isPromosi = dataUji.isPromosi,
                    isMemenuhiTarget = salesPrediction.result,
                    salesPredictionDetail = salesPrediction,
                    prediksi = salesPrediction.result.hasilPrediksi()
                )
                call.respond(
                    message = onSuccess(result),
                    status = httpCode
                )
            }
        }*/
    }
}