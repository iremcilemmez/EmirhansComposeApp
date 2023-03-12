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
            is HomeError.GetListError -> localizationManager.getString(R.string.error_message_list_some)
            is HomeError.AddToFavouritesError -> localizationManager.getString(R.string.error_message_add_to_favourites)
            else -> localizationManager.getString(R.string.error_message_list_unknown)
        }

}