package com.eemmez.favourite.domain.usecase

import com.eemmez.common.domain.di.IoDispatcher
import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError>> =
        favouriteRepository.getAll().flowOn(ioDispatcher)
}