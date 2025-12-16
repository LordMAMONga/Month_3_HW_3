package com.geeks.hw_3.ui.notes_add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.hw_3.data.model.NotesModel
import com.geeks.hw_3.databinding.FragmentAddNotesBinding
import com.geeks.hw_3.ui.App
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNotes : Fragment() {
    private lateinit var binding: FragmentAddNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val realTime = formatter.format(Date()).toString()

        binding.btnSave.setOnClickListener {
            val title = binding.titleDetail.text.toString()
            val desc = binding.descDetail.text.toString()

            App.database.dao()
                .addNote(NotesModel(notesTitle = title, notesDesc = desc, notesTime = realTime))

            findNavController().navigateUp()
        }
        binding.time.text = realTime.toString()
    }

}