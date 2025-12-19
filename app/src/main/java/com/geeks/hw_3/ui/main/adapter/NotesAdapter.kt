package com.geeks.hw_3.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geeks.hw_3.data.model.NotesModel
import com.geeks.hw_3.databinding.ItemNotesBinding

class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    val notesList= arrayListOf<NotesModel>()

    fun addNotes(notes: List<NotesModel>){
        notesList.clear()
        notesList.addAll(notes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: NotesViewHolder,
        position: Int
    ) {
        holder.onBind(notesList[position])
    }

    override fun getItemCount() = notesList.size

    class NotesViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: NotesModel) {
            binding.apply {
                notesTitle.text = model.notesTitle
                notesDesc.text = model.notesDesc
                notesTime.text = model.notesTime.toString()
            }

        }
    }
}