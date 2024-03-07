package com.example.myapplication

class ToDoCellRepository {

    private val todoCellsList : MutableList<ToDoCell>

    init{
        val list = mutableListOf<ToDoCell>()

        for(i in 1..20){
            val item = ToDoCell(
                todoId = i,
                todoText = "Todo num $i",
                isDone = i % 2 == 0, // Alternate completion status
                doUntilDate = "24.02"
            )
            list.add(item)
        }
        todoCellsList = list

    }

    fun getTodo(): List<ToDoCell>{
        return todoCellsList
    }
}