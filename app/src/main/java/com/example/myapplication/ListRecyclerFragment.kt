package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentListRecyclerBinding


class ListRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentListRecyclerBinding
    private val repository = ToDoCellRepository()
    private val todoCells = repository.getTodo()
    private val adapter = ToDoCellAdapter(todoCells)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(context)
    }
}