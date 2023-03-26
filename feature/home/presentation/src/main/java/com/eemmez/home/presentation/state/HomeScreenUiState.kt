package com.eemmez.home.presentation.state

import com.eemmez.home.domain.entity.ListItemEntity

sealed class HomeScreenUiState {
    data class Empty(val emptyMessage: String) : HomeScreenUiState()
    object Loading : HomeScreenUiState()
    data class Error(val errorMessage: String) : HomeScreenUiState()
    data class Content(val list: List<ListItemEntity>) : HomeScreenUiState()
}
