package com.eemmez.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.eemmez.home.presentation.component.HomeTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        rule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            Navigation(navController = navController)
        }
    }

    @Test
    fun isHomeScreen_startDestination() {
        rule.onNodeWithTag(HomeTag.route).assertIsDisplayed()
    }
}