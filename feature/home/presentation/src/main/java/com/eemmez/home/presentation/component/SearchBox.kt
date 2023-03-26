package com.eemmez.home.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(modifier: Modifier = Modifier.fillMaxWidth(), onTextChanged: (String) -> Unit) {
    val searchValue = rememberSaveable { mutableStateOf("") }
    Box(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = searchValue.value,
            onValueChange = {
                searchValue.value = it
                onTextChanged.invoke(it)
            },
            label = { Text("Search...") })
    }
}