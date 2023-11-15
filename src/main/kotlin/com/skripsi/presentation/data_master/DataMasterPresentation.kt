package com.skripsi.presentation.data_master

import com.skripsi.base.BasePresentation
import com.skripsi.domain.usecases.GetListDataTransaksiUseCase
import com.skripsi.domain.usecases.master.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

object DataMasterPresentation : BasePresentation() {
    private val getListPenjualanUseCase: GetListPenjualanUseCase
            by inject(GetListPenjualanUseCase::class.java)
    private val getListPembelianUseCase: GetListPembelianUseCase
            by inject(GetListPembelianUseCase::class.java)
    private val getListBarangUseCase: GetListBarangUseCase
            by inject(GetListBarangUseCase::class.java)
    private val getListDataTransaksiUseCase: GetListDataTransaksiUseCase
            by inject(GetListDataTransaksiUseCase::class.java)


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

    fun getDataTransaksi(route: Route) {
        route.get("data_transaksi") {
            responseResult {
                call.respond(
                    message = onSuccess(getListDataTransaksiUseCase()),
                    status = it
                )
            }
        }
    }

}