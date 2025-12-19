package com.geeks.hw_3.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.geeks.hw_3.R
import com.geeks.hw_3.data.model.NotesModel
import com.geeks.hw_3.databinding.ItemNotesBinding

class NotesAdapter(val onLongClick: (NotesModel) -> Unit, val onClick: (NotesModel) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    val notesList = arrayListOf<NotesModel>()

    fun addNotes(notes: List<NotesModel>) {
        Log.d("ololo", "Пришло заметок: ${notes.size}")
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

    inner class NotesViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: NotesModel) {
            binding.apply {
                notesTitle.text = model.notesTitle
                notesDesc.text = model.notesDesc
                notesTime.text = model.notesTime

                if (model.color != null){
                    notesBack.setCardBackgroundColor(
                        ContextCompat.getColor(root.context, model.color)
                    )
                }else{
                    notesBack.setCardBackgroundColor(
                        ContextCompat.getColor(root.context, R.color.orange)
                    )
                }
            }
            itemView.setOnLongClickListener {
                onLongClick(model)
                false
            }
            itemView.setOnClickListener {
                onClick(model)
            }


        }
    }
}