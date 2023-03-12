package com.eemmez.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eemmez.detail.presentation.component.DetailItem
import com.vngrs.detail.domain.entity.DetailEntity

@Composable
fun DetailScreen(detailEntity: DetailEntity) {
    Box(modifier = Modifier.fillMaxSize()) {

        DetailItem(detailEntity = detailEntity)
    }
}
const val detailRoute = "detail"