package com.example.mijnmedicijnkastje.screens.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mijnmedicijnkastje.database.DagelijkseMedicatie
import com.example.mijnmedicijnkastje.databinding.DetailDagMedInListBinding


class DagMedAdapter(val clickListener: DagMedClickListener) :
    ListAdapter<DagelijkseMedicatie, DagMedAdapter.ViewHolder>(DagMedDiffCallback()) {

    private var _deleteButtonTouched = MutableLiveData<Boolean>()
    val deleteButtonTouched: MutableLiveData<Boolean>
        get() {
            return _deleteButtonTouched
        }

    private var _medicijn = MutableLiveData<DagelijkseMedicatie?>()
    val medicijn: MutableLiveData<DagelijkseMedicatie?>
        get() {
            return _medicijn
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
        holder.binding.deleteDagMed.setOnClickListener {
            _medicijn.value = holder.binding.dagMed!!
            _deleteButtonTouched.value = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: DetailDagMedInListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            dagmed: DagelijkseMedicatie,
            clickListener: DagMedClickListener
        ) {
            binding.dagMed = dagmed
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DetailDagMedInListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class DagMedDiffCallback : DiffUtil.ItemCallback<DagelijkseMedicatie>() {

    override fun areItemsTheSame(
        oldItem: DagelijkseMedicatie,
        newItem: DagelijkseMedicatie
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DagelijkseMedicatie,
        newItem: DagelijkseMedicatie
    ): Boolean {
        return oldItem == newItem
    }
}

class DagMedClickListener(val clickListener: (dagelijkseMedicatie: DagelijkseMedicatie) -> Unit) {
    fun onClick(dagelijkseMedicatie: DagelijkseMedicatie) = clickListener(dagelijkseMedicatie)
}