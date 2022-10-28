package com.example.mijnmedicijnkastje.screens.medicijnkast

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
    private var _medicijn = MutableLiveData<MedicijnInKast?>()
    val medicijn: MutableLiveData<MedicijnInKast?>
        get() {
            return _medicijn
        }
    private var _increaseAantalTouched = MutableLiveData<Boolean>()
    val increaseAantalTouched: MutableLiveData<Boolean>
        get() {
            return _increaseAantalTouched
        }
    private var _decreaseAantalTouched = MutableLiveData<Boolean>()
    val decreaseAantalTouched: MutableLiveData<Boolean>
        get() {
            return _decreaseAantalTouched
        }

    init {
        _deleteButtonTouched.value = false
        _meerInfoButtonTouched.value = false
        _medicijn.value = null
        _increaseAantalTouched.value = false
        _decreaseAantalTouched.value = false
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
        holder.binding.btnVerwijderMedicijn.setOnClickListener {
            _medicijn.value = holder.binding.medicijn!!
            _deleteButtonTouched.value = true
        }
        holder.binding.meerInfoMedicijn.setOnClickListener {
            _meerInfoButtonTouched.value = true
        }
        holder.binding.btnIncreaseDosis.setOnClickListener {
            _medicijn.value = holder.binding.medicijn!!
            _medicijn.value!!.aantal = _medicijn.value!!.aantal.plus(1)
            holder.binding.kiesAantalDosissen.setText(_medicijn.value!!.aantal.toString())
            _increaseAantalTouched.value = true
        }
        holder.binding.btnDecreaseDosis.setOnClickListener {
            _medicijn.value = holder.binding.medicijn!!
            if (_medicijn.value!!.aantal > 0) {
                _medicijn.value!!.aantal = _medicijn.value!!.aantal.plus(-1)
            } else {
                _medicijn.value!!.aantal = 0
            }
            holder.binding.kiesAantalDosissen.setText(_medicijn.value!!.aantal.toString())
            _decreaseAantalTouched.value = true
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
            binding.kiesAantalDosissen.setText(medicijn.aantal.toString())
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
