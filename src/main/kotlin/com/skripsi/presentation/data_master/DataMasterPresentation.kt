package com.skripsi.presentation.data_master

import com.skripsi.base.BasePresentation
import com.skripsi.domain.models.DataUjiPembelian
import com.skripsi.domain.models.DataUjiRaw
import com.skripsi.domain.models.ResultBuyRecommendation
import com.skripsi.domain.usecases.GetListDataMentahUseCase
import com.skripsi.domain.usecases.GetListDataTrainingPenjualanUseCase
import com.skripsi.domain.usecases.GetSalesPredictionUseCase
import com.skripsi.domain.usecases.master.*
import com.skripsi.utils.labeledPenjualan
import com.skripsi.utils.labeledStok
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

object DataMasterPresentation : BasePresentation() {
    private val getListPenjualanUseCase: GetListPenjualanUseCase
            by inject(GetListPenjualanUseCase::class.java)
    private val getListGolonganUseCase: GetListGolonganUseCase
            by inject(GetListGolonganUseCase::class.java)
    private val getListKategoriUseCase: GetListKategoriUseCase
            by inject(GetListKategoriUseCase::class.java)
    private val getListPembelianUseCase: GetListPembelianUseCase
            by inject(GetListPembelianUseCase::class.java)
    private val getListBarangUseCase: GetListBarangUseCase by inject(GetListBarangUseCase::class.java)
    private val getListDataMentahUseCase: GetListDataMentahUseCase by inject(GetListDataMentahUseCase::class.java)
    private val getListDataTrainingPenjualanUseCase: GetListDataTrainingPenjualanUseCase
            by inject(GetListDataTrainingPenjualanUseCase::class.java)

    private val getSalesPredictionUseCase: GetSalesPredictionUseCase
            by inject(GetSalesPredictionUseCase::class.java)


    fun getPenjualan(route: Route) {
        route.get("penjualan") {
            responseResult {
                call.respond(
                    message = onSuccess(
                        getListPenjualanUseCase()
                    ),
                    status = it
                )
            }
        }
    }

    fun getPembelian(route: Route) {
        route.get("pembelian") {
            responseResult {
                call.respond(
                    message = onSuccess(
                        getListPembelianUseCase()
                    ),
                    status = it
                )
            }
        }
    }

    fun getGolongan(route: Route) {
        route.get("golongan") {
            responseResult {
                call.respond(
                    message = onSuccess(getListGolonganUseCase()),
                    status = it
                )
            }
        }
    }

    fun getKategori(route: Route) {
        route.get("kategori") {
            responseResult {
                call.respond(
                    message = onSuccess(getListKategoriUseCase()),
                    status = it
                )
            }
        }
    }

    fun getBarang(route: Route) {
        route.get("barang") {
            responseResult {
                call.respond(
                    message = onSuccess(getListBarangUseCase()),
                    status = it
                )
            }
        }
    }

    fun getDataMentah(route: Route) {
        route.get("data_mentah") {
            responseResult {
                call.respond(
                    message = onSuccess(getListDataMentahUseCase()),
                    status = it
                )
            }
        }
    }

    fun getDataTrainingPembelian(route: Route) {
        route.get("data_training") {
            responseResult {
                call.respond(
                    message = onSuccess(getListDataTrainingPenjualanUseCase(getListDataMentahUseCase())),
                    status = it
                )
            }
        }
    }

    fun getSalesPrediction(
        route: Route
    ) {
        route.post("/calculate") {
            responseResult { httpCode ->
                val dataUji = call.receive<DataUjiRaw>()
                val buyRecommendation = getSalesPredictionUseCase(
                    getListDataTrainingPenjualanUseCase(getListDataMentahUseCase()),
                    DataUjiPembelian(
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