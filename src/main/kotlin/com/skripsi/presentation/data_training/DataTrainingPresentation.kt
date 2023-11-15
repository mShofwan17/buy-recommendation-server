package com.skripsi.presentation.data_training

import com.skripsi.base.BasePresentation
import com.skripsi.domain.models.DataUji
import com.skripsi.domain.models.DataUjiTransaksi
import com.skripsi.domain.models.ResultBuyRecommendation
import com.skripsi.domain.usecases.CalculateBuyRecommendedUseCase
import com.skripsi.domain.usecases.GetListDataTrainingUseCase
import com.skripsi.domain.usecases.GetListDataTransaksiUseCase
import com.skripsi.utils.labeledPenjualan
import com.skripsi.utils.labeledStok
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

object DataTrainingPresentation : BasePresentation() {
    private val getListDataTransaksiUseCase: GetListDataTransaksiUseCase
            by inject(GetListDataTransaksiUseCase::class.java)
    private val getListDataTrainingUseCase: GetListDataTrainingUseCase
            by inject(GetListDataTrainingUseCase::class.java)
    private val calculateBuyRecommendedUseCase: CalculateBuyRecommendedUseCase
            by inject(CalculateBuyRecommendedUseCase::class.java)

    fun getDataTraining(route: Route) {
        route.get("data_training") {
            responseResult {
                call.respond(
                    message = onSuccess(
                        getListDataTrainingUseCase(
                            getListDataTransaksiUseCase()
                        )
                    ),
                    status = it
                )
            }
        }
    }

    fun getBuyRecommended(
        route: Route
    ) {
        route.post("/calculate") {
            responseResult { httpCode ->
                val dataUjis = call.receive<List<DataUjiTransaksi>>()
                val dataTraining = getListDataTrainingUseCase(getListDataTransaksiUseCase())
                val results = dataUjis.map { dataUji ->
                    val buyRecommendation = calculateBuyRecommendedUseCase(
                        dataTraining,
                        DataUji(
                            kodeBarang = dataUji.kodeBarang,
                            namaBarang = dataUji.namaBarang,
                            kategori = dataUji.kategori,
                            stok = dataUji.stok.labeledStok(),
                            isDiskon = dataUji.isDiskon,
                            penjualan = dataUji.penjualan.labeledPenjualan()
                        )
                    )

                    ResultBuyRecommendation(
                        kodeBarang = dataUji.kodeBarang,
                        namaBarang = dataUji.namaBarang,
                        kategori = dataUji.kategori,
                        stok = dataUji.stok,
                        isDiskon = dataUji.isDiskon,
                        penjualan = dataUji.penjualan,
                        recommendation = buyRecommendation,
                    )
                }

                call.respond(
                    message = onSuccess(results),
                    status = httpCode
                )
            }
        }
    }
}