package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.CellRepository
import com.example.myapplication.databinding.FragmentListRecyclerBinding
import kotlinx.coroutines.launch


class ListRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentListRecyclerBinding
    private val viewModel: CellsViewModel by activityViewModels()
    private val repository = CellRepository()
    private val todoCells = repository.getCellList() //лист пока пустой
    private val adapter = CellAdapter(todoCells)


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

        viewModel.updateList(0, 20)
    }

    private fun init() = with(binding) {
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(context)
    }
}