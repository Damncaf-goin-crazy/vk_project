package com.example.myapplication

//дата класс для инфы
data class ToDoCell(
    val todoId: Int,
    val todoText: String,
    val isDone: Boolean,
    val doUntilDate: String
)
