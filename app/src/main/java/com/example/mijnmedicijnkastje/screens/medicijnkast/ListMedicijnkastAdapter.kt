package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import com.example.mijnmedicijnkastje.databinding.DetailMedicijnkastInListBinding


class ListMedicijnkastAdapter(
    var clickListener: MedicijnInKastClickListener
) :
    ListAdapter<MedicijnInKast, ListMedicijnkastAdapter.ViewHolder>(MedicijnDiffCallback()) {

    private var _deleteButtonTouched = MutableLiveData<Boolean>()
    val deleteButtonTouched: MutableLiveData<Boolean>
        get() {
            return _deleteButtonTouched
        }

    private var _meerInfoButtonTouched = MutableLiveData<Boolean>()
    val meerInfoButtonTouched: MutableLiveData<Boolean>
        get() {
            return _meerInfoButtonTouched
        }

    init {
        _deleteButtonTouched.value = false
        _meerInfoButtonTouched.value = false
    }

    private var _medicijn = MutableLiveData<MedicijnInKast?>()
    val medicijn: MutableLiveData<MedicijnInKast?>
        get() {
            return _medicijn
        }

    init {
        _medicijn.value = null
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
        holder.binding.btnVerwijderMedicijn.setOnClickListener {
            Log.i("lstmedadap", holder.binding.medicijn?.naam.toString())
            _medicijn.value = holder.binding.medicijn!!
            _deleteButtonTouched.value = true
        }
        holder.binding.meerInfoMedicijn.setOnClickListener {
            _meerInfoButtonTouched.value = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: DetailMedicijnkastInListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            medicijn: MedicijnInKast,
            clickListener: MedicijnInKastClickListener
        ) {
            binding.medicijn = medicijn
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DetailMedicijnkastInListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class MedicijnDiffCallback : DiffUtil.ItemCallback<MedicijnInKast>() {

    override fun areItemsTheSame(oldItem: MedicijnInKast, newItem: MedicijnInKast): Boolean {
        return oldItem.registratienr == newItem.registratienr
    }

    override fun areContentsTheSame(oldItem: MedicijnInKast, newItem: MedicijnInKast): Boolean {
        return oldItem == newItem
    }
}

class MedicijnInKastClickListener(val clickListener: (medicijn: MedicijnInKast) -> Unit) {
    fun onClick(medicijn: MedicijnInKast) = clickListener(medicijn)
    fun remove(medicijn: MedicijnInKast) = clickListener(medicijn)
//    fun showInfo(medicijn: MedicijnInKast) = clickListener(medicijn)
}
