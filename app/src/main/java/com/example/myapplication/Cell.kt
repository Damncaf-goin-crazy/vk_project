package com.example.myapplication

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

//дата класс для json
data class Cell(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnail")
    val thumbnail: String,
)
