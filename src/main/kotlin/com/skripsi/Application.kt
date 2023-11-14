package com.skripsi

import com.skripsi.di.configureKoin
import com.skripsi.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>)=
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
