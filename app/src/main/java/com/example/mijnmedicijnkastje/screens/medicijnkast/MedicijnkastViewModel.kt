package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import kotlinx.coroutines.launch

class MedicijnkastViewModel(val database: MedicijnDatabaseDAO, application: Application) :
    AndroidViewModel(application) {

//    fun increaseDosis() {
//        _aantal.value = _aantal.value?.plus(1)
//    }
//
//    fun decreaseDosis() {
//        if (_aantal.value!! > 0) {
//            _aantal.value = aantal.value?.plus(-1)
//        } else {
//            _aantal.value = 0
//        }
//    }

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

    private var _id = MutableLiveData<Int?>()
    val id: MutableLiveData<Int?>
        get() {
            return _id
        }

    init {
        _medicijn.value = null
        _id.value = 0
    }

    fun clickMedicijn(medicijn: MedicijnInKast) {
        _medicijn.value = medicijn
    }

    fun delete() {
        viewModelScope.launch {
            _medicijn.value?.let { database.delete(it) }
        }


    }
}