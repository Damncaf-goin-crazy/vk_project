package com.example.myapplication

import com.google.gson.annotations.SerializedName


//дата класс для json
data class Cell(
    val title: String,
    val description: String,
    val thumbnail: String,
)
