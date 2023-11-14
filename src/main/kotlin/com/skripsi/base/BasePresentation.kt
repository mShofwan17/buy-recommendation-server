package com.skripsi.base

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

abstract class BasePresentation {
    private var _httpCode = HttpStatusCode.OK
    private val dataIsNull = "No Data"

    suspend fun PipelineContext<Unit, ApplicationCall>.responseResult(
        successResult: suspend (httpCode: HttpStatusCode) -> Unit
    ) {
        try {
            _httpCode = HttpStatusCode.OK
            successResult.invoke(HttpStatusCode.OK)
        } catch (e: Exception) {
            _httpCode = HttpStatusCode.NotFound
            call.respond(
                status = HttpStatusCode.NotFound,
                message = onError(
                    exceptionMsg = e.message
                )
            )
        } catch (e: IllegalArgumentException) {
            _httpCode = HttpStatusCode.BadRequest
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = onError(
                    exceptionMsg = e.message
                )
            )
        } catch (e: Exception) {
            _httpCode = HttpStatusCode.InternalServerError
            call.respond(
                status = _httpCode,
                message = onError(
                    exceptionMsg = e.message
                )
            )
        }
    }

    private fun onError(
        exceptionMsg: String? = null
    ): BaseResponse<String?> {
        return BaseResponse(
            statusCode = _httpCode.value,
            message = _httpCode.description,
            result = exceptionMsg ?: dataIsNull
        )
    }

    fun <T> onSuccess(result: T) = BaseResponse(
        statusCode = _httpCode.value,
        message = when (result) {
            is List<*> -> _httpCode.description + " with Record : ${result.count()}"
            else -> _httpCode.description
        },
        result
    )

    suspend fun ApplicationCall.emptyResult() {
        respond(
            message = onError(),
            status = _httpCode
        )
    }

    suspend fun ApplicationCall.failedUpdatedData(id: Int?) {
        _httpCode = HttpStatusCode.BadRequest
        respond(
            message = onError(exceptionMsg = "Gagal Update Data : $id"),
            status = _httpCode
        )
    }
}