package com.example.noteappnewapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.noteappnewapp.MainActivity
import com.example.noteappnewapp.R
import com.example.noteappnewapp.databinding.FragmentSecondBinding
import com.example.noteappnewapp.model.Note
import com.example.noteappnewapp.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: ViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            _binding = FragmentSecondBinding.inflate(
                inflater,container,false
            )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).viewModel
        mView = view
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.editTextNote.text.toString().trim()
        val noteDesc = binding.editTextNote2.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteDesc)

            noteViewModel.addNote(note)
            Toast.makeText(context, "Noted Saved", Toast.LENGTH_SHORT).show()
            //  Snackbar.make(
            //      view, "Note saved successfully",
            //     Snackbar.LENGTH_SHORT
            // ).show()
            view.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)

        } else {
            Toast.makeText(activity, "Please enter", Toast.LENGTH_SHORT).show()
        }
        /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            menu.clear()
            inflater.inflate(R.menu.menu_new_note, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }*/

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.second_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_save -> {
                    saveNote(mView)
                }
            }
            return super.onOptionsItemSelected(item)
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}