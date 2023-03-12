package com.eemmez.favourite.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.presentation.component.List
import com.vngrs.common.presentation.component.ProgressDialog

@Composable
fun FavouriteRoute(
    onItemClick: (FavouriteItemEntity) -> Unit,
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    val favouriteScreenUiState by viewModel.favouriteScreenUiState.collectAsStateWithLifecycle()

    FavouriteScreen(
        favouriteScreenUiState = favouriteScreenUiState,
        onItemClick = onItemClick,
        onItemLongClick = { favouriteItemEntity ->
            viewModel.deleteFromFavourites(favouriteItemEntity)
        }
    )
}

@Composable
fun FavouriteScreen(
    favouriteScreenUiState: FavouriteScreenUiState,
    onItemClick: (FavouriteItemEntity) -> Unit,
    onItemLongClick: (FavouriteItemEntity) -> Unit
) {
    when (favouriteScreenUiState) {
        is FavouriteScreenUiState.Content -> {
            List(
                listItems = favouriteScreenUiState.list,
                onItemClick = onItemClick,
                onItemLongClick = onItemLongClick
            )
        }

        is FavouriteScreenUiState.Empty -> {

        }

        is FavouriteScreenUiState.Loading -> {
            ProgressDialog()
        }

        is FavouriteScreenUiState.Error -> {

        }
    }
}

