package com.eemmez.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import com.eemmez.home.domain.usecase.AddToFavouritesUseCase
import com.eemmez.home.domain.usecase.GetListUseCase
import com.eemmez.home.presentation.HomeRoute
import com.eemmez.home.presentation.HomeViewModel
import com.eemmez.home.presentation.component.HomeTag
import com.eemmez.home.presentation.mapper.ErrorMessageMapper
import com.eemmez.localization.LocalizationManager
import com.eemmez.presentation.fake.FakeHomeRepository
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test

class HomeUiTest {
    @get:Rule
    val rule = createComposeRule()

    private val fakeHomeRepository = FakeHomeRepository()
    private val ioDispatcher = Dispatchers.IO

    private val getListUseCase = GetListUseCase(fakeHomeRepository, ioDispatcher)
    private val addToFavouritesUseCase = AddToFavouritesUseCase(fakeHomeRepository, ioDispatcher)
    private val localizationManager =
        LocalizationManager(ApplicationProvider.getApplicationContext())
    private val errorMessageMapper = ErrorMessageMapper(localizationManager)

    @Test
    fun isShown_searchBox() {
        rule.setContent {
            HomeRoute(
                onItemClick = {},
                viewModel = HomeViewModel(
                    getListUseCase,
                    addToFavouritesUseCase,
                    errorMessageMapper,
                    localizationManager
                )
            )
        }

        rule.onNodeWithTag(HomeTag.searchTextField).assertExists()
    }
}