package com.example.myapplication.api.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products")
    val list: List<CellDto>
)
