package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import com.example.mijnmedicijnkastje.models.MockupMedicijnkastDB

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

    private val _verwijderMedicijnUitKast = MutableLiveData<Boolean>()
    val verwijderMedicijnUitKast: LiveData<Boolean>
        get() {
            return _verwijderMedicijnUitKast
        }

    init {
        _verwijderMedicijnUitKast.value = false
    }

    fun btnVerwijderMedicijnClicked() {
        _verwijderMedicijnUitKast.value = true
    }

    private val _meerInfoMedicijn = MutableLiveData<Boolean>()
    val meerInfoMedicijn: LiveData<Boolean>
        get() {
            return _meerInfoMedicijn
        }

    init {
        _meerInfoMedicijn.value = false
    }

    fun btnMeerInfoClicked() {
        _meerInfoMedicijn.value = true
    }

    private var _medicijnkast = MockupMedicijnkastDB().getMedicijnen()

    val medicijnkast: LiveData<List<MedicijnInKast>>
        get() {
            return _medicijnkast
        }

    private var _medicijn = MutableLiveData<MedicijnInKast?>()
    val medicijn: MutableLiveData<MedicijnInKast?>
        get() {
            return _medicijn
        }

    init {
        _medicijn.value = null
    }

    fun clickMedicijn(medicijn: MedicijnInKast) {
        _medicijn.value = medicijn
    }

    fun removeMedicijn(medicijn: MedicijnInKast) {
//        viewModelScope.launch {
//            database.delete(medicijn)
//        }
        var lst = _medicijnkast.value?.toMutableList()
        if (lst != null) {
            lst.remove(medicijn)
        }
        _medicijnkast = MutableLiveData(lst)
        Log.i("MedicijnkastVM", "Verwijderen gelukt")

    }
}
