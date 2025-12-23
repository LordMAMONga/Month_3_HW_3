package com.geeks.hw_3.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.geeks.hw_3.data.model.NotesModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_list")
    fun getAllNotes(): List<NotesModel>

    @Query("SELECT * FROM note_list WHERE notesTitle LIKE :searchText || '% '")
    fun searchByTitle(searchText: String): List<NotesModel>

    @Insert
    fun addNote(notesModel: NotesModel)

    @Delete
    fun deleteNotes(notesModel: NotesModel)

    @Update
    fun updateNotes(notesModel: NotesModel)

}