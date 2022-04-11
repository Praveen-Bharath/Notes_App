package com.example.noteappnewapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappnewapp.MainActivity
import com.example.noteappnewapp.R
import com.example.noteappnewapp.databinding.FragmentThirdBinding
import com.example.noteappnewapp.model.Note
import com.example.noteappnewapp.viewmodel.ViewModel

class ThirdFragment : Fragment(R.layout.fragment_third) {


    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private val args : ThirdFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).viewModel
        currentNote = args.note!!

        binding.editTextNoteUpdate.setText(currentNote.desc)
        binding.editTextNote2Update.setText(currentNote.title)

        binding.floatingActionButton3.setOnClickListener {
            val title = binding.editTextNoteUpdate.text.toString().trim()
            val body = binding.editTextNote2Update.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)

                view.findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)

            } else {
                Toast.makeText(activity, "Please enter title ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to permanently delete this note?")
            setPositiveButton("DELETE") { _, _ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(
                    R.id.action_thirdFragment_to_firstFragment
                )
            }
            setNegativeButton("CANCEL", null)
        }.create().show()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.third_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}