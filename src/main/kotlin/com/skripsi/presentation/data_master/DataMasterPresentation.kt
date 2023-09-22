package com.skripsi.presentation.data_master

import com.skripsi.base.BasePresentation
import com.skripsi.domain.usecases.master.GetListGolonganUseCase
import com.skripsi.domain.usecases.master.GetListKategoriUseCase
import com.skripsi.domain.usecases.master.GetListPembelianUseCase
import com.skripsi.domain.usecases.master.GetListPenjualanUseCase
import io.ktor.server.application.*
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
}