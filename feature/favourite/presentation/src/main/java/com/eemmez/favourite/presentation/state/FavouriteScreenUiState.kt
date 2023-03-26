package com.eemmez.favourite.presentation.state

import com.eemmez.favourite.domain.entity.FavouriteItemEntity

sealed class FavouriteScreenUiState {
    data class Empty(val emptyMessage: String) : FavouriteScreenUiState()
    object Loading : FavouriteScreenUiState()
    data class Content(val list: List<FavouriteItemEntity>) : FavouriteScreenUiState()
    data class Error(val errorMessage: String) : FavouriteScreenUiState()
}
