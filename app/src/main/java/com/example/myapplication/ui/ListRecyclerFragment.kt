package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CellAdapter
import com.example.myapplication.databinding.FragmentListRecyclerBinding
import kotlinx.coroutines.launch

class ListRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentListRecyclerBinding

    private val rvAdapter: CellAdapter by lazy { CellAdapter() }
    private val myLayoutManager by lazy { LinearLayoutManager(context) }
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTodos.apply {
            layoutManager = myLayoutManager
            adapter = rvAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (rvAdapter.currentList.size - myLayoutManager.findLastCompletelyVisibleItemPosition() < 10) {
                        viewModel.loadCellsVm()
                    }
                }
            })
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Content -> {
                        //binding.rvTodos.alpha = 1f
                        rvAdapter.submitList(state.list)
                    }

                    is UiState.Error -> {
                        //binding.rvTodos.alpha = 1f
                    }

                    UiState.Loading -> {
                        //binding.rvTodos.alpha = 0.4f
                    }
                }
            }


        }
    }

}