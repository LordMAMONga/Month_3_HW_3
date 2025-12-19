package com.geeks.hw_3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geeks.hw_3.R
import com.geeks.hw_3.data.local.AppDatabase
import com.geeks.hw_3.databinding.FragmentMainBinding
import com.geeks.hw_3.ui.App
import com.geeks.hw_3.ui.main.adapter.NotesAdapter

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter = NotesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotes.adapter = adapter
        adapter.addNotes(App.database.dao().getAllNotes())

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addNotes)
        }

    }


}

