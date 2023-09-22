package com.skripsi.presentation.data_master

import com.skripsi.base.BasePresentation
import com.skripsi.domain.usecases.GetListPenjualanUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

object DataMasterPresentation : BasePresentation() {
    private val getListPenjualanUseCase: GetListPenjualanUseCase
            by inject(GetListPenjualanUseCase::class.java)

    fun getPenjualan(route: Route){
        route.get("penjualan") {
            responseResult {
                call.respond(
                    message = onSuccess(getListPenjualanUseCase()),
                    status = it
                )
            }
        }
    }
}