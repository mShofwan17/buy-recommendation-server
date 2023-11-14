package com.skripsi.presentation.data_training

import io.ktor.server.routing.*

fun Route.dataTrainingRouting() {
    route("trainings") {
        DataTrainingPresentation.apply {
            getDataTraining(this@route)
            getBuyRecommended(this@route)
        }
    }
}