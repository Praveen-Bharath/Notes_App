package com.example.noteappnewapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteappnewapp.MainActivity
import com.example.noteappnewapp.R
import com.example.noteappnewapp.adapter.AdapterNote
import com.example.noteappnewapp.databinding.FragmentFirstBinding
import com.example.noteappnewapp.model.Note
import com.example.noteappnewapp.viewmodel.ViewModel


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var bindingone: FragmentFirstBinding? = null
    private val binding get() = bindingone!!
    private lateinit var  viewModelNote : ViewModel
    private lateinit var adapterNote : AdapterNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingone = FragmentFirstBinding.inflate(
            inflater,container,false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelNote = (activity as MainActivity).viewModel
        setRecyclerView()

        binding.floatingActionButton2.setOnClickListener{ mView ->
            mView.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    private fun setRecyclerView() {
        adapterNote = AdapterNote()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = adapterNote
        }

        activity?.let {
            viewModelNote.getAllNotes().observe(viewLifecycleOwner, { note ->
                adapterNote.differ.submitList(note)
                updateUI(note)
            })
        }

    }
    private fun updateUI(note: List<Note>) {
        if (note.isNotEmpty()) {
            binding.cardView2.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.cardView2.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingone = null

    }

}