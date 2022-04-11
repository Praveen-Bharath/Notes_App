package com.example.noteappnewapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappnewapp.model.Note
import com.example.noteappnewapp.repository.RepositoryNote
import kotlinx.coroutines.launch

class ViewModel (
    app: Application,
    private val repositoryNote: RepositoryNote
        ): AndroidViewModel(app){

            fun addNote(note: Note) = viewModelScope.launch {
                repositoryNote.addNote(note)
            }
            fun updateNote(note: Note) = viewModelScope.launch {
                repositoryNote.updateNote(note)
            }
            fun deleteNote(note: Note) = viewModelScope.launch {
                repositoryNote.deleteNote(note)
            }
            fun getAllNotes() = repositoryNote.getAllNotes()
}