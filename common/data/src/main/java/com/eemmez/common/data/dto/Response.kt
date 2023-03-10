package com.eemmez.common.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Response<T, E>(
    val status: Status,
    val result: T?,
    val errorCode: E?
)
