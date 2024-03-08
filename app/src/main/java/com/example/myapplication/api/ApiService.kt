package com.example.myapplication.api

import com.example.myapplication.Cell
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Query

//во время вызова не забыть указать два параметра
interface ApiService {

    @GET("/products")
    fun getCells(
        @Query("start") start: Int, //Начальный индекс фетча
        @Query("limit") limit: Int // Количество айтемов для фетча
    ): Call<List<Cell>>

    //TODO: сделать импорт картинок
    @Multipart
    @GET("/products")
    fun getPic(
        @Query("start") start: Int, //Начальный индекс фетча
        @Query("limit") limit: Int // Количество айтемов для фетча
    )
}