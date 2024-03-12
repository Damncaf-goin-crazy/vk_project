package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.Cell
import com.example.myapplication.EachCellFragmentArgs
import com.example.myapplication.databinding.FragmentEachCellBinding
import com.google.gson.Gson


class EachCellFragment : Fragment() {

    private lateinit var binding: FragmentEachCellBinding
    private val navArgs: EachCellFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEachCellBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init(): Unit = with(binding) {
        val gson = Gson()
        val cell = gson.fromJson(navArgs.cell, Cell::class.java)
        description.text = cell.description
        name.text = cell.title
        Glide.with(requireContext())
            .load(cell.thumbnail)
            .into(ivThumbnailFrg)
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }


}