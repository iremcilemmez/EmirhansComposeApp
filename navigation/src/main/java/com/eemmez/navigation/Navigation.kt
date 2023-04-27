package com.eemmez.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eemmez.detail.presentation.DetailNavigation
import com.eemmez.detail.presentation.DetailScreen
import com.eemmez.favourite.presentation.FavouriteNavigation
import com.eemmez.favourite.presentation.FavouriteRoute
import com.eemmez.home.presentation.HomeNavigation
import com.eemmez.home.presentation.HomeRoute
import com.eemmez.navigation.mapper.toDetailEntity
import com.eemmez.navigation.util.ParcelableType
import com.eemmez.navigation.util.extension.getSafeParcelable
import com.vngrs.detail.domain.entity.DetailEntity

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavigation.route
    ) {
        composable(HomeNavigation.route) {
            HomeRoute(
                onItemClick = { homeListItem ->
                    val detailEntityArg = viewModel.getParcelableString(homeListItem.toDetailEntity())
                    navController.navigate("${DetailNavigation.route}/$detailEntityArg")
                }
            )
        }
        composable(FavouriteNavigation.route) {
            FavouriteRoute(
                onItemClick = { favouriteListItem ->
                    val detailEntityArg = viewModel.getParcelableString(favouriteListItem.toDetailEntity())
                    navController.navigate("${DetailNavigation.route}/$detailEntityArg")
                }
            )
        }
        composable(
            route = DetailNavigation.route,
            arguments = listOf(navArgument(DetailNavigation.detailEntityArg) {
                type = ParcelableType(DetailEntity::class.java, viewModel.gson())
            })
        ) {
            it.arguments?.getSafeParcelable(DetailNavigation.detailEntityArg, DetailEntity::class.java)
                ?.let { detailEntityArg ->
                    DetailScreen(detailEntity = detailEntityArg)
                }
        }
    }
}