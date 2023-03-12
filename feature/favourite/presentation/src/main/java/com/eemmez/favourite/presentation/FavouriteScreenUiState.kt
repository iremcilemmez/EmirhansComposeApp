package com.eemmez.favourite.presentation

import com.eemmez.favourite.domain.entity.FavouriteItemEntity

sealed class FavouriteScreenUiState {
    object Empty : FavouriteScreenUiState()
    object Loading : FavouriteScreenUiState()
    data class Content(val list: List<FavouriteItemEntity>) : FavouriteScreenUiState()
    data class Error(val errorMessage: String) : FavouriteScreenUiState()
}
