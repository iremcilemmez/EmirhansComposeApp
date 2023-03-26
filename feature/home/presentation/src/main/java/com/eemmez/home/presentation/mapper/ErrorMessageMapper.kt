package com.eemmez.home.presentation.mapper

import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.presentation.R
import com.eemmez.localization.LocalizationManager
import javax.inject.Inject

class ErrorMessageMapper @Inject constructor(
    private val localizationManager: LocalizationManager
) {
    fun getErrorMessage(error: HomeError?): String =
        when (error) {
            is HomeError.GetListError -> localizationManager.getString(R.string.some_list_error)
            is HomeError.AddToFavouritesError -> localizationManager.getString(R.string.add_favourite_error)
            else -> localizationManager.getString(R.string.list_unknown_error)
        }

}