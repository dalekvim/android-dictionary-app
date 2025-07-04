package com.example.dictionaryapp.ui.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.network.DictionaryApi
import com.example.dictionaryapp.model.WordEntry
import kotlinx.coroutines.launch

class DictionaryViewModel: ViewModel() {
    fun getDef(word: String) {
        viewModelScope.launch {
            dictionaryUiState = DictionaryUiState.Loading
            dictionaryUiState = try {
                val wordDefinition = DictionaryApi.retrofitService.getMeaning(word)
                DictionaryUiState.Success(wordDefinition)
            } catch (e: Exception) {
                DictionaryUiState.Error(e)
            }
        }
    }

    var dictionaryUiState: DictionaryUiState by mutableStateOf(DictionaryUiState.Loading)
        private set
}

sealed interface DictionaryUiState {
    data class Success(val definition: List<WordEntry>): DictionaryUiState
    data class Error(val e: Exception): DictionaryUiState
    object Loading: DictionaryUiState
}