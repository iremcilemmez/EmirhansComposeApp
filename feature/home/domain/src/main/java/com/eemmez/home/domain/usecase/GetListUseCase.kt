package com.eemmez.home.domain.usecase

import com.eemmez.common.domain.di.IoDispatcher
import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.GetListResponseEntity
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(
        pageNumber: Int,
        search: String
    ): Flow<Result<GetListResponseEntity, HomeError>> =
        homeRepository.getList(pageNumber, search)
            .flowOn(ioDispatcher)
}