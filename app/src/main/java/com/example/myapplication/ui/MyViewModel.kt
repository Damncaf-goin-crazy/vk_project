package com.example.myapplication.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.CellRepository
import com.example.myapplication.network.MyConnectivityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow<UiState>(UiState.Content(emptyList()))
    val uiState = _uiState.asStateFlow()
    private val repository = CellRepository()
    private val myConnectivityManager = MyConnectivityManager(application, viewModelScope)

    private val isConnected: AtomicBoolean = AtomicBoolean(true)

    init {
        subscribeToInternet()
        loadCellsVm()
    }

    private var job: Job? = null

    private fun subscribeToInternet() {
        viewModelScope.launch(Dispatchers.IO) {
            myConnectivityManager.connectionAsStateFlow.collect { connected ->
                isConnected.set(connected)
                if(connected){
                    loadCellsVm()
                }
            }
        }
    }

    fun loadCellsVm() {
        if (uiState.value !is UiState.Loading && isConnected.get()) {
            job?.cancel()
            job = viewModelScope.launch(Dispatchers.IO) {
                _uiState.emit(UiState.Loading)
                _uiState.emit(repository.loadCells())
            }
        }
    }
}