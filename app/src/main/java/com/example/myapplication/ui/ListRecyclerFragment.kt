package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CellAdapter
import com.example.myapplication.databinding.FragmentListRecyclerBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ListRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentListRecyclerBinding

    private val rvAdapter: CellAdapter by lazy {
        CellAdapter { cell ->
            val gson = Gson()
            val cellJson = gson.toJson(cell)
            val action = ListRecyclerFragmentDirections.actionToCell(cellJson)
            findNavController().navigate(action)
        }
    }
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
            val myLayoutManager = LinearLayoutManager(context)
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
                        val text = state.message
                        val duration = Toast.LENGTH_LONG
                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }

                    UiState.Loading -> {
                        //binding.rvTodos.alpha = 0.4f
                        //обработать анимацию загрузки, если необходимо
                    }
                }
            }


        }

    }

}