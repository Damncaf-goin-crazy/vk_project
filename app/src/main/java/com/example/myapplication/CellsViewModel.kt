package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.CellRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CellsViewModel: ViewModel()  {

    private val repository = CellRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCell(0, 20)
        }
    }

    fun updateList(startIndex: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCell(startIndex, limit)
        }
    }
}