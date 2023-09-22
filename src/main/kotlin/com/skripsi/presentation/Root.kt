package com.skripsi.presentation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root() {
    get("/") {
        call.respond(
            message = "Welcome to Sales Prediction",
            status = HttpStatusCode.OK
        )
    }
}