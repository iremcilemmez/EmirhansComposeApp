package com.eemmez.favourite.domain.entity

sealed class FavouriteError {
    object GetFavouritesError : FavouriteError()
    object InsertError : FavouriteError()
    object DeleteError : FavouriteError()
}
