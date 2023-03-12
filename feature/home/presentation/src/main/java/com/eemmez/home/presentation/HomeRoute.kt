package com.eemmez.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.presentation.component.List
import com.eemmez.home.presentation.component.SearchBox
import com.eemmez.home.presentation.component.Template
import com.vngrs.common.presentation.component.ProgressDialog

@Composable
fun HomeRoute(
    onItemClick: (ListItemEntity) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeScreenUiState by viewModel.homeScreenUiState.collectAsStateWithLifecycle()

    val homeScreenUiEvent by viewModel.homeScreenUiEvent.collectAsStateWithLifecycle()

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
            homeScreenUiEvent = homeScreenUiEvent
        )
    }
}

@Composable
private fun HomeScreen(
    onItemClick: (ListItemEntity) -> Unit,
    onItemLongClick: (ListItemEntity) -> Unit,
    homeScreenUiState: HomeScreenUiState,
    homeScreenUiEvent: HomeScreenUiEvent
) {

    when (homeScreenUiState) {
        is HomeScreenUiState.Content -> {
            HomeScreenContent(
                list = homeScreenUiState.list,
                onItemClick = onItemClick,
                onItemLongClick = onItemLongClick
            )
        }

        is HomeScreenUiState.Loading -> {
            ProgressDialog()
        }

        is HomeScreenUiState.Error -> {

        }

        else -> Unit
    }

    when (homeScreenUiEvent) {
        is HomeScreenUiEvent.AddToFavouriteSuccess -> {

        }

        else -> Unit
    }
}

@Composable
private fun HomeScreenContent(
    list: List<ListItemEntity>,
    onItemClick: (ListItemEntity) -> Unit,
    onItemLongClick: (ListItemEntity) -> Unit
) {
    List(
        listItems = list,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick
    )
}