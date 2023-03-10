package com.eemmez.home.presentation

data class AddToFavouritesUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
