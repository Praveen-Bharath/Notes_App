package com.example.noteappnewapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteappnewapp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun upadateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY id")
    fun getAllNotes(): LiveData<List<Note>>
}