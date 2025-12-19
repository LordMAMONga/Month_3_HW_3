package com.geeks.hw_3.ui.notes_add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.geeks.hw_3.R
import com.geeks.hw_3.data.model.NotesModel
import com.geeks.hw_3.databinding.ColorWindowBinding
import com.geeks.hw_3.databinding.FragmentAddNotesBinding
import com.geeks.hw_3.ui.App
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNotes : Fragment() {
    private lateinit var binding: FragmentAddNotesBinding
    private val args: AddNotesArgs by navArgs()
    private var selectedColor: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.note?.let {
            binding.titleDetail.setText(it.notesTitle)
            binding.descDetail.setText(it.notesDesc)
            selectedColor = it.color
        }

        binding.icCharge.setOnClickListener {
            showColorPicker()
        }

        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val realTime = formatter.format(Date()).toString()

        binding.btnSave.setOnClickListener {
            val title = binding.titleDetail.text.toString()
            val desc = binding.descDetail.text.toString()
            binding.time.text = realTime
            val note: NotesModel? = args.note

            if (note != null) {
                App.database.dao().updateNotes(
                    NotesModel(
                        notesTitle = title,
                        notesDesc = desc,
                        notesTime = realTime,
                        id = note.id,
                        color = selectedColor
                    )
                )
            } else {
                App.database.dao()
                    .addNote(
                        NotesModel(
                            notesTitle = title,
                            notesDesc = desc,
                            notesTime = realTime,
                            color = selectedColor
                        )
                    )
            }

            findNavController().navigateUp()
        }


    }

    private fun showColorPicker() {
        val inflater = LayoutInflater.from(requireContext())
        val colorBinding = ColorWindowBinding.inflate(inflater)

        val popupWindow = PopupWindow(
            colorBinding.root,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        val colorClickListener = View.OnClickListener { view ->
            selectedColor = when (view.id) {
                R.id.color_yellow -> R.color.color_window_yellow
                R.id.color_purple -> R.color.color_window_purple
                R.id.color_pink -> R.color.color_window_pink
                R.id.color_orange -> R.color.color_window_orange
                R.id.color_green -> R.color.color_window_green
                R.id.color_blue -> R.color.color_window_blue
                else -> null
            }
            popupWindow.dismiss()
        }
        colorBinding.colorYellow.setOnClickListener(colorClickListener)
        colorBinding.colorPurple.setOnClickListener(colorClickListener)
        colorBinding.colorPink.setOnClickListener(colorClickListener)
        colorBinding.colorOrange.setOnClickListener(colorClickListener)
        colorBinding.colorGreen.setOnClickListener(colorClickListener)
        colorBinding.colorBlue.setOnClickListener(colorClickListener)

        popupWindow.showAsDropDown(binding.icCharge)
    }

}