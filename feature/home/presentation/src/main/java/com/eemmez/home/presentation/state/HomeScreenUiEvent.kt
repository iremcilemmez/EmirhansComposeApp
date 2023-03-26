package com.eemmez.home.presentation.state

sealed class HomeScreenUiEvent {
    object Idle : HomeScreenUiEvent()
    object Loading: HomeScreenUiEvent()
    data class Error(val errorMessage: String) : HomeScreenUiEvent()
    data class AddFavouriteSuccess(val successMessage: String) : HomeScreenUiEvent()
}
