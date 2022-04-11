package com.example.noteappnewapp.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappnewapp.R
import com.example.noteappnewapp.databinding.LayoutAdapterBinding
import com.example.noteappnewapp.fragments.FirstFragment
import com.example.noteappnewapp.fragments.FirstFragmentDirections
import com.example.noteappnewapp.model.Note

class AdapterNote : RecyclerView.Adapter<AdapterNote.ViewHolderNote>() {

    private var binding : LayoutAdapterBinding?= null
    class ViewHolderNote(itemBinding: LayoutAdapterBinding):
            RecyclerView.ViewHolder(itemBinding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    val differ =AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNote {
            binding= LayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolderNote(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolderNote, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemView.apply {
            binding?.editNoteTitle?.text = currentNote.title
            binding?.editNoteDesc?.text = currentNote.desc
        }.setOnClickListener{ mView ->
            val direction = FirstFragmentDirections
                .actionFirstFragmentToThirdFragment(currentNote)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}