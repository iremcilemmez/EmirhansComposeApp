package com.eemmez.favourite.domain.usecase

import com.eemmez.common.domain.di.IoDispatcher
import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError>> =
        flow {
            val result = favouriteRepository.getAll()
            emit(result)
        }.onStart {
            emit(Result.Loading())
        }.catch {
            emit(Result.Error(FavouriteError.GetFavouritesError))
        }.flowOn(ioDispatcher)
}
