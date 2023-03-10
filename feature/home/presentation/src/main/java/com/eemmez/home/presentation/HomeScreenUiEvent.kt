package com.eemmez.home.presentation

sealed class HomeScreenUiEvent {
    object Initial: HomeScreenUiEvent()
    object AddToFavouriteSuccess : HomeScreenUiEvent()
}
