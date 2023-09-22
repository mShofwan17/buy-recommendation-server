package com.skripsi.plugins

import com.skripsi.presentation.data_training.dataTrainingRouting
import com.skripsi.presentation.root
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        root()
        dataTrainingRouting()
    }
}
