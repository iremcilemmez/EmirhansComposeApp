package com.eemmez.home.domain.entity

sealed class HomeError {
    object GetListError : HomeError()
    object AddToFavouritesError: HomeError()
    object UnknownError : HomeError()
}
