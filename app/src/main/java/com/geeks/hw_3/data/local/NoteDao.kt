package com.geeks.hw_3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.geeks.hw_3.data.model.NotesModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_list")
    fun getAllNotes(): List<NotesModel>

    @Insert
    fun addNote(notesModel: NotesModel)
}