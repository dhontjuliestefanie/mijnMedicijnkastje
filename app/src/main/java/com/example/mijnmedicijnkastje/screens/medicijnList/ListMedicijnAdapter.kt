package com.example.mijnmedicijnkastje.screens.medicijnList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mijnmedicijnkastje.databinding.DetailMedicijnInListBinding
import com.example.mijnmedicijnkastje.models.Medicijn

class ListMedicijnAdapter(val clickListener: MedicijnClickListener): ListAdapter<Medicijn, ListMedicijnAdapter.ViewHolder>(MedicijnDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: DetailMedicijnInListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            medicijn: Medicijn,
            clickListener: MedicijnClickListener
        ) {
            binding.medicijn = medicijn
            binding.clickListener = clickListener
//            itembinding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DetailMedicijnInListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class MedicijnDiffCallback : DiffUtil.ItemCallback<Medicijn>() {

    override fun areItemsTheSame(oldItem: Medicijn, newItem: Medicijn): Boolean {
        return oldItem.registratienr == newItem.registratienr
    }

    override fun areContentsTheSame(oldItem: Medicijn, newItem: Medicijn): Boolean {
        return oldItem == newItem
    }
}

class MedicijnClickListener(val clickListener: (medicijn: Medicijn) -> Unit) {
    fun onClick(medicijn: Medicijn) = clickListener(medicijn)
}