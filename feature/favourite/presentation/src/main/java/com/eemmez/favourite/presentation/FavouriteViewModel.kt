package com.eemmez.favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.usecase.DeleteFromFavouritesUseCase
import com.eemmez.favourite.domain.usecase.GetFavouritesUseCase
import com.eemmez.favourite.presentation.mapper.ErrorMessageMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val deleteFromFavouritesUseCase: DeleteFromFavouritesUseCase,
    private val errorMessageMapper: ErrorMessageMapper
) : ViewModel() {

    private val _favouriteScreenUiState =
        MutableStateFlow<FavouriteScreenUiState>(FavouriteScreenUiState.Loading)
    val favouriteScreenUiState: StateFlow<FavouriteScreenUiState> = _favouriteScreenUiState

    init {
        getFavourites()
    }

    private fun getFavourites() {
        viewModelScope.launch {
            getFavouritesUseCase.invoke().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _favouriteScreenUiState.value =
                            if (result.data.isNullOrEmpty())
                                FavouriteScreenUiState.Empty
                            else
                                FavouriteScreenUiState.Content(result.data!!)
                    }

                    is Result.Loading -> {
                        _favouriteScreenUiState.value = FavouriteScreenUiState.Loading
                    }

                    is Result.Error -> {
                        _favouriteScreenUiState.value =
                            FavouriteScreenUiState.Error(errorMessageMapper.getErrorMessage(result.error))
                    }
                }
            }
        }
    }

    fun deleteFromFavourites(favouriteItemEntity: FavouriteItemEntity) {
        viewModelScope.launch {
            deleteFromFavouritesUseCase.invoke(favouriteItemEntity).collect { result ->
                when (result) {
                    is Result.Success -> {
                        getFavourites()
                    }

                    is Result.Loading -> {
                        _favouriteScreenUiState.value = FavouriteScreenUiState.Loading
                    }

                    is Result.Error -> {
                        _favouriteScreenUiState.value =
                            FavouriteScreenUiState.Error(errorMessageMapper.getErrorMessage(result.error))
                    }
                }
            }
        }
    }
}