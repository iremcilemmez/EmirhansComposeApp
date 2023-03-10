package com.eemmez.home.presentation

import com.eemmez.home.domain.entity.ListItemEntity

sealed class HomeScreenUiState {
    object Initial : HomeScreenUiState()
    object Loading : HomeScreenUiState()
    data class Error(val errorMessage: String) : HomeScreenUiState()
    data class Content(val list: List<ListItemEntity>) : HomeScreenUiState()
}
