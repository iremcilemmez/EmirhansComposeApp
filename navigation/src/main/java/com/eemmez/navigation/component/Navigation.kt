package com.eemmez.navigation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eemmez.detail.presentation.DetailScreen
import com.eemmez.detail.presentation.detailRoute
import com.eemmez.favourite.presentation.FavouriteRoute
import com.eemmez.home.presentation.HomeRoute
import com.eemmez.navigation.mapper.toDetailEntity
import com.eemmez.navigation.util.extension.getParcelable
import com.eemmez.navigation.util.extension.putParcelable
import com.vngrs.detail.domain.entity.DetailEntity

@Suppress("DEPRECATION")
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeRoute(
                onItemClick = { homeListItem ->
                    navController.putParcelable("detailEntity", homeListItem.toDetailEntity())
                    navController.navigate(detailRoute)
                }
            )
        }
        composable("favourite") {
            FavouriteRoute(
                onItemClick = { favouriteListItem ->
                    navController.putParcelable("detailEntity", favouriteListItem.toDetailEntity())
                    navController.navigate(detailRoute)
                }
            )
        }
        composable(detailRoute) {
            navController.getParcelable("detailEntity", DetailEntity::class.java)
                ?.let { detailArgument ->
                    DetailScreen(detailArgument)
                }
        }
    }
}