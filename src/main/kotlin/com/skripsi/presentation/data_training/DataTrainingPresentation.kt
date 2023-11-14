package com.skripsi.presentation.data_training

import com.skripsi.base.BasePresentation
import com.skripsi.domain.models.DataUji
import com.skripsi.domain.models.DataUjiTransaksi
import com.skripsi.domain.models.ResultBuyRecommendation
import com.skripsi.domain.usecases.GetBuyRecommendedUseCase
import com.skripsi.domain.usecases.GetListDataTrainingPenjualanUseCase
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
    private val getListDataTrainingPenjualanUseCase: GetListDataTrainingPenjualanUseCase
            by inject(GetListDataTrainingPenjualanUseCase::class.java)
    private val getBuyRecommendedUseCase: GetBuyRecommendedUseCase
            by inject(GetBuyRecommendedUseCase::class.java)

    fun getDataTraining(route: Route) {
        route.get("data_training") {
            responseResult {
                call.respond(
                    message = onSuccess(
                        getListDataTrainingPenjualanUseCase(
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
                val dataUji = call.receive<DataUjiTransaksi>()
                val buyRecommendation = getBuyRecommendedUseCase(
                    getListDataTrainingPenjualanUseCase(getListDataTransaksiUseCase()),
                    DataUji(
                        kodeBarang = dataUji.kodeBarang,
                        namaBarang = dataUji.namaBarang,
                        kategori = dataUji.kategori,
                        stok = labeledStok(dataUji.stok),
                        isDiskon = dataUji.isDiskon,
                        penjualan = labeledPenjualan(dataUji.penjualan)
                    )
                )

                val result = ResultBuyRecommendation(
                    kodeBarang = dataUji.kodeBarang,
                    namaBarang = dataUji.namaBarang,
                    kategori = dataUji.kategori,
                    stok = dataUji.stok,
                    isDiskon = dataUji.isDiskon,
                    penjualan = dataUji.penjualan,
                    recommendation = buyRecommendation,
                )
                call.respond(
                    message = onSuccess(result),
                    status = httpCode
                )
            }
        }
    }
}