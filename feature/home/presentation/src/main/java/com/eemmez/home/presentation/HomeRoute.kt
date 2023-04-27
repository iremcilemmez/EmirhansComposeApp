package com.eemmez.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.presentation.component.List
import com.eemmez.home.presentation.component.SearchBox
import com.eemmez.home.presentation.component.HomeTag
import com.eemmez.home.presentation.component.Template
import com.eemmez.home.presentation.state.HomeScreenUiEvent
import com.eemmez.home.presentation.state.HomeScreenUiState
import com.vngrs.common.presentation.component.EmptyMessage
import com.vngrs.common.presentation.component.ErrorDialog
import com.vngrs.common.presentation.component.ProgressDialog

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier.testTag(HomeTag.route),
    onItemClick: (ListItemEntity) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeScreenUiState by viewModel.homeScreenUiState.collectAsStateWithLifecycle()
    val homeScreenUiEvent by viewModel.homeScreenUiEvent.collectAsStateWithLifecycle()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Template(topBar = {
        SearchBox(onTextChanged = { searchText ->
            viewModel.getList(0, searchText)
        })
    }) {
        HomeScreen(
            onItemClick = onItemClick,
            onItemLongClick = { listItemEntity ->
                viewModel.addToFavourites(listItemEntity)
            },
            homeScreenUiState = homeScreenUiState,
            homeScreenUiEvent = homeScreenUiEvent,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun HomeScreen(
    onItemClick: (ListItemEntity) -> Unit,
    onItemLongClick: (ListItemEntity) -> Unit,
    homeScreenUiState: HomeScreenUiState,
    homeScreenUiEvent: HomeScreenUiEvent,
    snackbarHostState: SnackbarHostState
) {
    when (homeScreenUiState) {
        is HomeScreenUiState.Content -> {
            List(
                listItems = homeScreenUiState.list,
                onItemClick = onItemClick,
                onItemLongClick = onItemLongClick
            )
        }

        is HomeScreenUiState.Loading -> {
            ProgressDialog()
        }

        is HomeScreenUiState.Error -> {
            ErrorDialog(errorMessage = homeScreenUiState.errorMessage)
        }

        is HomeScreenUiState.Empty -> {
            EmptyMessage(message = homeScreenUiState.emptyMessage)
        }
    }

    when (homeScreenUiEvent) {
        is HomeScreenUiEvent.AddFavouriteSuccess -> {
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(homeScreenUiEvent.successMessage)
            }
        }
        is HomeScreenUiEvent.Loading -> {
            ProgressDialog()
        }

        is HomeScreenUiEvent.Error -> {
            ErrorDialog(errorMessage = homeScreenUiEvent.errorMessage)
        }
        else -> Unit
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter)
    ) {
        SnackbarHost(modifier = Modifier.testTag(HomeTag.snackbar), hostState = snackbarHostState)
    }
}
