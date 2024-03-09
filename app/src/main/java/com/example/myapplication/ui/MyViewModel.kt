package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.CellRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Content(emptyList()))
    val uiState = _uiState.asStateFlow()

    private val repository = CellRepository()

    init {
        loadCellsVm()
    }

    fun loadCellsVm() {
        if (uiState.value !is UiState.Loading) {
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.emit(UiState.Loading)
                _uiState.emit(repository.loadCells())
            }
        }
    }
}