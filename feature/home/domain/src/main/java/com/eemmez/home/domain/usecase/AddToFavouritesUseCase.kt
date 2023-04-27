package com.eemmez.home.domain.usecase

import com.eemmez.common.domain.di.IoDispatcher
import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(listItemEntity: ListItemEntity): Flow<Result<Unit, HomeError>> =
        flow {
            val result = homeRepository.addToFavourites(listItemEntity)
            emit(result)
        }.onStart {
            emit(Result.Loading())
        }.catch {
            emit(Result.Error(HomeError.AddToFavouritesError))
        }.flowOn(ioDispatcher)
}
