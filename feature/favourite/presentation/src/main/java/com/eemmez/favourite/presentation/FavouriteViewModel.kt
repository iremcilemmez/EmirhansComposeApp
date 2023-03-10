package com.eemmez.favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.usecase.DeleteFromFavouritesUseCase
import com.eemmez.favourite.domain.usecase.GetFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val deleteFromFavouritesUseCase: DeleteFromFavouritesUseCase
) : ViewModel() {

    fun getFavourites() {
        viewModelScope.launch {
            getFavouritesUseCase.invoke().collect {

            }
        }
    }

    fun deleteFromFavourites(favouriteItemEntity: FavouriteItemEntity) {
        viewModelScope.launch {
            deleteFromFavouritesUseCase.invoke(favouriteItemEntity).collect {

            }
        }
    }
}