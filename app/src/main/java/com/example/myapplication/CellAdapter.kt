package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CellAdapter(_todoCells: List<Cell>):
    RecyclerView.Adapter<CellAdapter.TodoCellsViewHolder>() {

    private var todoCells: List<Cell> = _todoCells


    class TodoCellsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cellTitle: TextView = itemView.findViewById(R.id.tvName)
        private val descriptionOfCell: TextView = itemView.findViewById(R.id.tvDescription)
        private val cellThumbnail: TextView = itemView.findViewById(R.id.tvThumbnail)
        fun onBind(cellItem: Cell) {
            cellTitle.text = cellItem.title
            descriptionOfCell.text = cellItem.description
            cellThumbnail.text = cellItem.thumbnail
        }
    }


    //возвращает виевхолдер нашего типа: TodoCellsViewHolder
    /* с помощью обЪекта LayoutInflater инфлейтит xml код в представление типа view
    * мы из нашего to_do_cell_layout создаем вьюшку которую можем использовать в коде
    * и она передается в конструктор TodoCellsViewHolder и он возвращается в методе
    *
    * атачтурут false */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoCellsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_cell_layout, parent, false)
        return TodoCellsViewHolder(view)    }

    //возвращает размер листа с тудушшками (датой)
    override fun getItemCount() = todoCells.size

    //в этом методе мы должны либо задать либо обновить данные для определенной вью
// получаем на вход наш вьюхолдер и позицию элемента в нем, и с помощью onBind
// добавляем элемент на позицию
    override fun onBindViewHolder(holder: TodoCellsViewHolder, position: Int) {
        holder.onBind(todoCells[position])
    }
}