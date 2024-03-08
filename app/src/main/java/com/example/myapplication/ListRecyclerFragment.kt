package com.example.myapplication

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.databinding.FragmentListRecyclerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOError
import java.io.IOException


class ListRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentListRecyclerBinding

    private lateinit var rvAdapter: CellAdapter
    private lateinit var cellsList: List<Cell>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cellsList = listOf()

        //init()

        lifecycleScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitBuilder.apiService.getCells()
            } catch (e: IOException) {
                //TODO: сделать тост
                return@launch
            } catch (e: HttpException) {
                //TODO: сделать тост
                return@launch
            }

            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    cellsList = response.body()!!
                    binding.rvTodos.apply {
                        rvAdapter = CellAdapter(cellsList)
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(context)
                    }
                }

            }

        }

    }

//    private fun init() = with(binding) {
//        rvTodos.adapter = adapter
//        rvTodos.layoutManager = LinearLayoutManager(context)
//    }
}