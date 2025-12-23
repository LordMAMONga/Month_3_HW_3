package com.geeks.hw_3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geeks.hw_3.data.model.NotesModel
import com.geeks.hw_3.databinding.FragmentMainBinding
import com.geeks.hw_3.ui.App
import com.geeks.hw_3.ui.main.adapter.NotesAdapter

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter = NotesAdapter(::onLongClick, ::onClick)

    override fun onResume() {
        super.onResume()

        adapter.addNotes(App.database.dao().getAllNotes())
    }

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
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddNotes(null))
        }

        binding.etNotes.doOnTextChanged { text, start, before, count ->
            adapter.addNotes(App.database.dao().searchByTitle(text.toString()))
        }
    }

    private fun onLongClick(notesModel: NotesModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить заметку?")
        builder.setPositiveButton("Удалить") { dialog, _ ->
            App.database.dao().deleteNotes(notesModel)
            adapter.addNotes(App.database.dao().getAllNotes())
            dialog.dismiss()
        }
        builder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun onClick(notesModel: NotesModel) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddNotes(notesModel))
    }

}

