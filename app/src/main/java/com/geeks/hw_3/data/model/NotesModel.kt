package com.geeks.hw_3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity("note_list")
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val notesTitle: String,
    val notesDesc: String,
    val notesTime: String,
    val color: Int? = null
): Serializable
