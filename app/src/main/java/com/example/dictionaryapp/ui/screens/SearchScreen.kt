package com.example.dictionaryapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dictionaryapp.ui.components.DisplayMeaning
import com.example.dictionaryapp.ui.components.MySearchBarM3

@Preview(showBackground = true)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    var showResults by remember { mutableStateOf(false) }

    val dictionaryViewModel: DictionaryViewModel = viewModel()

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        MySearchBarM3(onSearchClick = { searchQuery ->
            showResults = true
            dictionaryViewModel.getDef(searchQuery)
            keyboardController?.hide()
        })
        if (showResults) {
            DisplayMeaning(dictionaryUiState = dictionaryViewModel.dictionaryUiState)
        }
    }
}