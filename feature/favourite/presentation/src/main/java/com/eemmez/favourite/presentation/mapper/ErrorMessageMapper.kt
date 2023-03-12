package com.eemmez.favourite.presentation.mapper

import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.presentation.R
import com.eemmez.localization.LocalizationManager
import javax.inject.Inject

class ErrorMessageMapper @Inject constructor(
    private val localizationManager: LocalizationManager
){
    fun getErrorMessage(favouriteError: FavouriteError?): String =
        when (favouriteError) {
            is FavouriteError.GetFavouritesError -> localizationManager.getString(R.string.get_favourites_error)
            is FavouriteError.DeleteError -> localizationManager.getString(R.string.delete_favourite_error)
            else -> localizationManager.getString(R.string.unknown_error)
        }
}