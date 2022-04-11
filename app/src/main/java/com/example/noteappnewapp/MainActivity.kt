package com.example.noteappnewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.noteappnewapp.database.DatabaseNote
import com.example.noteappnewapp.databinding.ActivityMainBinding
import com.example.noteappnewapp.repository.RepositoryNote
import com.example.noteappnewapp.viewmodel.ViewModel
import com.example.noteappnewapp.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setViewModel()
    }

    private fun setViewModel(){
        val repositoryNote = RepositoryNote(
            DatabaseNote(this)
        )
        val viewModelProvider = ViewModelProviderFactory(
            application,repositoryNote
        )

        viewModel = ViewModelProvider(
            this,
            viewModelProvider
        )[ViewModel::class.java]
    }
}