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

class DeleteFavouriteUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(favouriteItemEntity: FavouriteItemEntity): Flow<Result<Unit, FavouriteError.DeleteError>> =
        flow {
            val result = favouriteRepository.delete(favouriteItemEntity)
            emit(result)
        }.onStart {
            emit(Result.Loading())
        }.catch {
            emit(Result.Error(FavouriteError.DeleteError))
        }.flowOn(ioDispatcher)
}