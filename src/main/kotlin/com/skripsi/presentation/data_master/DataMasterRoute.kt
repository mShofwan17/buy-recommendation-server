package com.skripsi.presentation.data_master

import io.ktor.server.routing.*

fun Route.dataMasterRouting() {
    route("master/") {
       DataMasterPresentation.apply {
           getPenjualan(this@route)
           getKategori(this@route)
           getGolongan(this@route)
           getPembelian(this@route)
       }
    }
}