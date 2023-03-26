package com.eemmez.home.presentation.state

sealed class HomeScreenUiEvent {
    object Idle : HomeScreenUiEvent()
    data class AddFavouriteSuccess(val successMessage: String) : HomeScreenUiEvent()
}
