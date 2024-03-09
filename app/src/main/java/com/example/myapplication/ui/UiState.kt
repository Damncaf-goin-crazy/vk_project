package com.example.myapplication.ui

import com.example.myapplication.Cell

sealed interface UiState {
    data class Error(val message: String) : UiState
    object Loading : UiState
    data class Content(val list: List<Cell>) : UiState
}