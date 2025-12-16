package com.geeks.hw_3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.hw_3.data.model.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun dao(): NoteDao
}