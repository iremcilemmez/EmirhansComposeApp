package com.eemmez.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.domain.usecase.AddToFavouritesUseCase
import com.eemmez.home.domain.usecase.GetListUseCase
import com.eemmez.home.presentation.mapper.HomeScreenErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val homeScreenErrorMapper: HomeScreenErrorMapper
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Initial)
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState
    private var currentPage = 0

    private val _homeScreenUiEvent = MutableStateFlow<HomeScreenUiEvent>(HomeScreenUiEvent.Initial)
    val homeScreenUiEvent: StateFlow<HomeScreenUiEvent> = _homeScreenUiEvent

    init {
        getList(0, "")
    }

    fun getList(pageNumber: Int, search: String) {
        viewModelScope.launch {
            getListUseCase.invoke(pageNumber, search)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            result.data?.let { response ->
                                _homeScreenUiState.value =
                                    HomeScreenUiState.Content(list = response.list)
                            }
                        }

                        is Result.Loading -> {
                            _homeScreenUiState.value = HomeScreenUiState.Loading
                        }

                        is Result.Error -> {
                            _homeScreenUiState.value = HomeScreenUiState.Error(
                                errorMessage = homeScreenErrorMapper.getErrorMessage(result.error)
                            )
                        }
                    }
                }
        }
    }

    fun addToFavourites(listItemEntity: ListItemEntity) {
        viewModelScope.launch {
            addToFavouritesUseCase.invoke(listItemEntity)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _homeScreenUiState.value = HomeScreenUiState.Initial
                        }

                        is Result.Loading -> {
                            _homeScreenUiState.value = HomeScreenUiState.Loading
                        }

                        is Result.Error -> {
                            _homeScreenUiState.value = HomeScreenUiState.Error(
                                errorMessage = homeScreenErrorMapper.getErrorMessage(result.error)
                            )
                        }
                    }
                }
        }
    }
}