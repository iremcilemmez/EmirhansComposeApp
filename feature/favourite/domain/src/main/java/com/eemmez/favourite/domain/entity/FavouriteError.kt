package com.eemmez.favourite.domain.entity

sealed class FavouriteError {
    object GetFavouritesError : FavouriteError()
    object DeleteError : FavouriteError()
}
