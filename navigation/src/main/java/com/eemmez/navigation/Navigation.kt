package com.eemmez.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eemmez.detail.presentation.DetailScreen
import com.eemmez.favourite.presentation.FavouriteRoute
import com.eemmez.home.presentation.HomeRoute
import com.eemmez.navigation.mapper.toDetailEntity
import com.eemmez.navigation.util.ParcelableType
import com.eemmez.navigation.util.extension.getSafeParcelable
import com.vngrs.detail.domain.entity.DetailEntity

@Suppress("DEPRECATION")
@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeRoute(
                onItemClick = { homeListItem ->
                    val detailEntityArg =
                        viewModel.getParcelableString(homeListItem.toDetailEntity())
                    navController.navigate("detail/$detailEntityArg")
                }
            )
        }
        composable("favourite") {
            FavouriteRoute(
                onItemClick = { favouriteListItem ->
                    val detailEntityArg =
                        viewModel.getParcelableString(favouriteListItem.toDetailEntity())
                    navController.navigate("detail/$detailEntityArg")
                }
            )
        }
        composable(
            route = "detail/{detailEntityArg}",
            arguments = listOf(navArgument("detailEntityArg") {
                type = ParcelableType(DetailEntity::class.java, viewModel.gson())
            })
        ) {
            it.arguments?.getSafeParcelable("detailEntityArg", DetailEntity::class.java)
                ?.let { detailEntityArg ->
                    DetailScreen(detailEntity = detailEntityArg)
                }
        }
    }
}