package com.example.dictionaryapp.ui.components

import androidx.compose.runtime.Composable
import com.example.dictionaryapp.ui.screens.DictionaryUiState
import com.example.dictionaryapp.ui.screens.ErrorScreen
import com.example.dictionaryapp.ui.screens.LoadingScreen

@Composable
fun DisplayMeaning(dictionaryUiState: DictionaryUiState) {
    when (dictionaryUiState) {
        is DictionaryUiState.Error -> ErrorScreen(e = dictionaryUiState.e)
        is DictionaryUiState.Loading -> LoadingScreen()
        is DictionaryUiState.Success -> DisplayDefinition(definition = dictionaryUiState.definition)
    }
}
