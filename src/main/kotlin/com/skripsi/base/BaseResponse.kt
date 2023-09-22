package com.skripsi.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val statusCode: Int = 200,
    val message: String = "Success",
    val result: T?
)
