package com.example.myapplication

import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.api.models.toCell
import com.example.myapplication.ui.UiState

class CellRepository {

    private val _list = mutableListOf<Cell>()
    private var startIndex = 0
    private val retrofitBuilder = RetrofitBuilder()

    suspend fun loadCells(): UiState {
        return try {
            val response = retrofitBuilder.apiService.getCells(startIndex, limit)
            val newCells = response.list.map { it.toCell() }
            _list.addAll(newCells)
            startIndex += limit
            UiState.Content(ArrayList(_list))
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
        }

    }

    companion object {
        private const val limit = 20
    }
}
