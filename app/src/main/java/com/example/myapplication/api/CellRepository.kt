package com.example.myapplication.api
//
//import com.example.myapplication.Cell
//import com.example.myapplication.api.RetrofitBuilder.apiService
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CellRepository {
//    private var listener: ToDoCellListener = object : ToDoCellListener {
//        override fun onDataFetched(todoCells: List<Cell>) {
//            //TODO: Handle the fetched data
//        }
//
//        override fun onError(errorMessage: String) {
//            //TODO: Handle the error
//        }
//    }
//
//    var todoCellsList = mutableListOf<Cell>()
//
//    fun getCell(startIndex: Int, limit: Int) {
//        val call: Call<List<Cell>> = apiService.getCells(startIndex, limit)
//
//        call.enqueue(object : Callback<List<Cell>> {
//            override fun onResponse(call: Call<List<Cell>>, response: Response<List<Cell>>) {
//                if (response.isSuccessful) {
//                    val newCells = response.body() ?: mutableListOf()
//                    todoCellsList.addAll(newCells)
//                    listener.onDataFetched(todoCellsList)
//                } else {
//                    // Обработка ошибки
//                    listener.onError("Error: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<Cell>>, t: Throwable) {
//                // Обработка ошибки
//                listener.onError("Error: ${t.message}")
//            }
//        })
//    }
//
//    fun getCellList(): MutableList<Cell> {
//        return todoCellsList
//    }
//
//    interface ToDoCellListener {
//        fun onDataFetched(todoCells: List<Cell>)
//        fun onError(errorMessage: String)
//    }
//}
