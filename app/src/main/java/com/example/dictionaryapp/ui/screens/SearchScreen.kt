package com.example.dictionaryapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dictionaryapp.ui.components.DisplayMeaning
import com.example.dictionaryapp.ui.components.MySearchBar

@Preview(showBackground = true)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    val onQueryChange: (String) -> Unit = { searchQuery = it }

    val dictionaryViewModel: DictionaryViewModel = viewModel()

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier
        .fillMaxSize()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        MySearchBar(searchQuery = searchQuery, onQueryChange = onQueryChange, onSearchClick = {
            dictionaryViewModel.getDef(searchQuery)
            keyboardController?.hide()
        })
        if (searchQuery.isNotBlank()) {
            DisplayMeaning(dictionaryUiState = dictionaryViewModel.dictionaryUiState)
        }
    }
}