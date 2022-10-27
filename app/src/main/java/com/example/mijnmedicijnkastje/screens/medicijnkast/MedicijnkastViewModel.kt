package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import kotlinx.coroutines.launch

class MedicijnkastViewModel(val database: MedicijnDatabaseDAO, application: Application) :
    AndroidViewModel(application) {

    private var _medicijnkast = database.getAllMedicijnen()

    val medicijnkast: LiveData<List<MedicijnInKast>>
        get() {
            return _medicijnkast
        }

    private var _medicijn = MutableLiveData<MedicijnInKast?>()
    val medicijn: MutableLiveData<MedicijnInKast?>
        get() {
            return _medicijn
        }

    private var _aantal = MutableLiveData<Int?>()
    val aantal: MutableLiveData<Int?>
        get() {
            return _aantal
        }

    init {
        _medicijn.value = null
        _aantal.value = 0
    }

    fun clickMedicijn(medicijn: MedicijnInKast) {
        _medicijn.value = medicijn
        _aantal.value = medicijn.aantal
    }

    fun delete() {
        viewModelScope.launch {
            _medicijn.value?.let { database.delete(it) }
        }
    }

    fun update() {
        viewModelScope.launch {
            database.update(medicijn.value!!)
        }
    }

    fun verlaagAantal() {
        if (medicijn.value?.aantal!! > 0) {
            _aantal.value = medicijn.value?.aantal?.plus(-1)!!
        } else {
            aantal.value = 0
        }
        update()
    }

    fun verhoogAantal() {
        _medicijn?.value?.aantal = medicijn.value?.aantal?.plus(1)!!
        Log.i("adapt", _medicijn.value?.aantal.toString())
        update()
    }
}