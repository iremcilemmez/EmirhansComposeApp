package com.eemmez.favourite.presentation.state

sealed class FavouriteScreenUiEvent {
    object Idle : FavouriteScreenUiEvent()
    data class Success(val successMessage: String) : FavouriteScreenUiEvent()
    object Loading : FavouriteScreenUiEvent()
    data class Error(val errorMessage: String) : FavouriteScreenUiEvent()
}
