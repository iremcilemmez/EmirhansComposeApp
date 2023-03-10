package com.eemmez.home.data.mapper

import com.eemmez.home.data.dto.HomeErrorCode
import com.eemmez.home.domain.entity.HomeError

object HomeErrorMapper {
    fun map(errorCode: HomeErrorCode?): HomeError {
        return when (errorCode) {
            HomeErrorCode.GET_LIST_ERROR -> HomeError.GetListError
            else -> HomeError.Unknown
        }
    }
}