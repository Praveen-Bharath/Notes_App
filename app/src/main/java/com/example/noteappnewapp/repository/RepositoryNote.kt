package com.example.noteappnewapp.repository

import com.example.noteappnewapp.database.DatabaseNote
import com.example.noteappnewapp.model.Note

class RepositoryNote(private val repoDB: DatabaseNote) {

    suspend fun addNote(note: Note) = repoDB.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = repoDB.getNoteDao().upadateNote(note)
    suspend fun deleteNote(note: Note) = repoDB.getNoteDao().deleteNote(note)
    fun getAllNotes() = repoDB.getNoteDao().getAllNotes()


}