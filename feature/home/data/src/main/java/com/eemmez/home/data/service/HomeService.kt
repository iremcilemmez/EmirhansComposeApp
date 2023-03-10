package com.eemmez.home.data.service

import com.eemmez.common.data.dto.Response
import com.eemmez.home.data.dto.GetListResponse
import com.eemmez.home.data.dto.HomeErrorCode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class HomeService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getList(
        pageNumber: Int?,
        search: String?
    ): Response<GetListResponse, HomeErrorCode> =
        client.get {
            url("list")
            parameter("pageNumber", pageNumber)
            parameter("search", search)
        }.body()
}