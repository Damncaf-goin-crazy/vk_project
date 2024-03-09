package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CellAdapter : ListAdapter<Cell, CellAdapter.TodoCellsViewHolder>(CellDiffCallBack()) {


    class TodoCellsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cellTitle: TextView = itemView.findViewById(R.id.tvName)
        private val descriptionOfCell: TextView = itemView.findViewById(R.id.tvDescription)
        private val cellThumbnail: TextView = itemView.findViewById(R.id.tvThumbnail)
        fun onBind(cellItem: Cell) {
            cellTitle.text = cellItem.title
            descriptionOfCell.text = cellItem.description
            cellThumbnail.text = cellItem.thumbnail
            //Glide
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoCellsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_cell_layout, parent, false)
        return TodoCellsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoCellsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class CellDiffCallBack : DiffUtil.ItemCallback<Cell>() {
    override fun areItemsTheSame(oldItem: Cell, newItem: Cell): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cell, newItem: Cell): Boolean =
        oldItem == newItem
}