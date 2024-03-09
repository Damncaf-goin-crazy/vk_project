package com.example.myapplication.api

import com.example.myapplication.api.models.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/products")
    suspend fun getCells(
        @Query("skip") start: Int,
        @Query("limit") limit: Int
    ): ProductsResponse

}