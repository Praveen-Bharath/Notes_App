package com.example.noteappnewapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.noteappnewapp.repository.RepositoryNote
import androidx.lifecycle.ViewModelProvider


class ViewModelProviderFactory(
    val app:Application,
    private val repositoryNote : RepositoryNote
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModel(app,repositoryNote) as T
    }
}