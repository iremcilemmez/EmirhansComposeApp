package com.eemmez.home.domain.usecase

import com.eemmez.common.domain.di.IoDispatcher
import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(listItemEntity: ListItemEntity): Flow<Result<Unit, HomeError>> =
        homeRepository.addToFavourites(listItemEntity).flowOn(ioDispatcher)
}