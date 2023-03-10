package com.eemmez.navigation.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eemmez.detail.presentation.DetailScreen
import com.eemmez.favourite.presentation.FavouriteScreen
import com.eemmez.home.presentation.HomeRoute
import com.eemmez.navigation.mapper.toDetailEntity
import com.eemmez.navigation.util.extension.getParcelable
import com.eemmez.navigation.util.extension.putParcelable
import com.vngrs.detail.domain.entity.DetailEntity

@Suppress("DEPRECATION")
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeRoute(
                onItemClick = { homeListItem ->
                    navController.putParcelable("detailEntity", homeListItem.toDetailEntity())
                    navController.navigate("detail")
                }
            )
        }
        composable("favourite") {
            FavouriteScreen(
                onItemClick = { favouriteListItem ->
                    navController.putParcelable("detailEntity", favouriteListItem.toDetailEntity())
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            navController.getParcelable("detailEntity", DetailEntity::class.java)
                ?.let { detailArgument ->
                    DetailScreen(detailArgument)
                }
        }
    }
}