package com.eemmez.favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.usecase.DeleteFavouriteUseCase
import com.eemmez.favourite.domain.usecase.GetFavouritesUseCase
import com.eemmez.favourite.presentation.mapper.ErrorMessageMapper
import com.eemmez.favourite.presentation.state.FavouriteScreenUiEvent
import com.eemmez.favourite.presentation.state.FavouriteScreenUiState
import com.eemmez.localization.LocalizationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val errorMessageMapper: ErrorMessageMapper,
    private val localizationManager: LocalizationManager
) : ViewModel() {

    private val _favouriteScreenUiState =
        MutableStateFlow<FavouriteScreenUiState>(FavouriteScreenUiState.Loading)
    val favouriteScreenUiState: StateFlow<FavouriteScreenUiState> = _favouriteScreenUiState

    private val _favouriteScreenUiEvent =
        MutableStateFlow<FavouriteScreenUiEvent>(FavouriteScreenUiEvent.Idle)
    val favouriteScreenUiEvent: StateFlow<FavouriteScreenUiEvent> = _favouriteScreenUiEvent

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
                                FavouriteScreenUiState.Empty(localizationManager.getString(R.string.no_favourite))
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
            deleteFavouriteUseCase.invoke(favouriteItemEntity).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _favouriteScreenUiEvent.value =
                            FavouriteScreenUiEvent.Success(localizationManager.getString(R.string.delete_favourite_success))
                        getFavourites()
                    }

                    is Result.Loading -> {
                        _favouriteScreenUiEvent.value = FavouriteScreenUiEvent.Loading
                    }

                    is Result.Error -> {
                        _favouriteScreenUiEvent.value =
                            FavouriteScreenUiEvent.Error(errorMessageMapper.getErrorMessage(result.error))
                    }
                }
            }
        }
    }
}