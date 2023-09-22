package com.skripsi.presentation.data_training

import com.skripsi.base.BasePresentation
import com.skripsi.base.BaseResponse
import com.skripsi.data.entities.DataTraining
import com.skripsi.domain.models.DataUji
import com.skripsi.domain.usecases.GetListDataTrainingUseCase
import com.skripsi.domain.usecases.GetSalesPredictionUseCase
import com.skripsi.utils.hasilPrediksi
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.ext.inject

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
    ) {
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
        }
    }
}