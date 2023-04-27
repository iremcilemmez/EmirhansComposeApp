package com.eemmez.presentation

import android.content.Context
import com.eemmez.home.domain.usecase.AddToFavouritesUseCase
import com.eemmez.home.domain.usecase.GetListUseCase
import com.eemmez.home.presentation.HomeViewModel
import com.eemmez.home.presentation.mapper.ErrorMessageMapper
import com.eemmez.home.presentation.state.HomeScreenUiEvent
import com.eemmez.home.presentation.state.HomeScreenUiState
import com.eemmez.localization.LocalizationManager
import com.eemmez.presentation.fake.FakeHomeRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeHomeRepository = FakeHomeRepository()
    private val ioDispatcher = Dispatchers.IO

    private val getListUseCase = GetListUseCase(fakeHomeRepository, ioDispatcher)
    private val addToFavouritesUseCase = AddToFavouritesUseCase(fakeHomeRepository, ioDispatcher)

    private val context = mockk<Context>(relaxed = true)
    private val localizationManager = LocalizationManager(context)
    private val errorMessageMapper = ErrorMessageMapper(localizationManager)

    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `expect loading state for homeScreenUiState when viewModel creates`() {
        homeViewModel = HomeViewModel(
            getListUseCase,
            addToFavouritesUseCase,
            errorMessageMapper,
            localizationManager
        )

        assert(homeViewModel.homeScreenUiState.value is HomeScreenUiState.Loading)
    }

    @Test
    fun `expect idle state for homeScreenUiEvent when viewModel creates`() {
        homeViewModel = HomeViewModel(
            getListUseCase,
            addToFavouritesUseCase,
            errorMessageMapper,
            localizationManager
        )

        assert(homeViewModel.homeScreenUiEvent.value is HomeScreenUiEvent.Idle)
    }

    @Test
    fun `expect content state getListUseCase`() {
        // Arrange
        homeViewModel = HomeViewModel(
            getListUseCase,
            addToFavouritesUseCase,
            errorMessageMapper,
            localizationManager
        )

        val expectedUiState = HomeScreenUiState.Content(fakeHomeRepository.homeItemList)

        // Act
        runTest {
            homeViewModel.getList(0, "")
        }
        // Assert
        val actualState = homeViewModel.homeScreenUiState.value
        assert(expectedUiState == actualState)
    }
}
