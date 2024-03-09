package com.example.myapplication.api.models

import com.example.myapplication.Cell
import com.google.gson.annotations.SerializedName

data class CellDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
)

fun CellDto.toCell(): Cell {
    return Cell(id, title, description, thumbnail)
}