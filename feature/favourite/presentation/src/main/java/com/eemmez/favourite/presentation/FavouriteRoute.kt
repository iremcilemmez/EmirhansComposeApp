package com.eemmez.favourite.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.presentation.component.List
import com.eemmez.favourite.presentation.state.FavouriteScreenUiEvent
import com.eemmez.favourite.presentation.state.FavouriteScreenUiState
import com.vngrs.common.presentation.component.EmptyMessage
import com.vngrs.common.presentation.component.ErrorDialog
import com.vngrs.common.presentation.component.ProgressDialog

@Composable
fun FavouriteRoute(
    onItemClick: (FavouriteItemEntity) -> Unit,
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    val favouriteScreenUiState by viewModel.favouriteScreenUiState.collectAsStateWithLifecycle()
    val favouriteScreenUiEvent by viewModel.favouriteScreenUiEvent.collectAsStateWithLifecycle()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    FavouriteScreen(
        favouriteScreenUiState = favouriteScreenUiState,
        favouriteScreenUiEvent = favouriteScreenUiEvent,
        snackbarHostState = snackbarHostState,
        onItemClick = onItemClick,
        onItemLongClick = { favouriteItemEntity ->
            viewModel.deleteFromFavourites(favouriteItemEntity)
        }
    )
}

@Composable
fun FavouriteScreen(
    favouriteScreenUiState: FavouriteScreenUiState,
    favouriteScreenUiEvent: FavouriteScreenUiEvent,
    snackbarHostState: SnackbarHostState,
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
        is FavouriteScreenUiState.Loading -> {
            ProgressDialog()
        }

        is FavouriteScreenUiState.Error -> {
            ErrorDialog(errorMessage = favouriteScreenUiState.errorMessage)
        }
        is FavouriteScreenUiState.Empty -> {
            EmptyMessage(message = favouriteScreenUiState.emptyMessage)
        }
    }

    when (favouriteScreenUiEvent) {
        is FavouriteScreenUiEvent.Success -> {
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(favouriteScreenUiEvent.successMessage)
            }
        }
        else -> Unit
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 72.dp)
            .wrapContentSize(align = Alignment.BottomCenter)
    ) {
        SnackbarHost(hostState = snackbarHostState)
    }
}

