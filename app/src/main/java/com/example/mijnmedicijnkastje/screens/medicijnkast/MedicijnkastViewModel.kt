package com.example.mijnmedicijnkastje.screens.medicijnkast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import com.example.mijnmedicijnkastje.models.MockupMedicijnDB

class MedicijnkastViewModel : ViewModel() {
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

    private val _medicijnkast = MockupMedicijnDB().getMedicijnen()
    val medicijnkast: LiveData<List<Medicijn>>
        get() {
            return _medicijnkast
        }

    private var _medicijn = MutableLiveData<Medicijn?>()
    val medicijn: MutableLiveData<Medicijn?>
        get() {
            return _medicijn
        }

    init {
        _medicijn.value = null
    }

    fun clickMedicijn(medicijn : Medicijn) {
        _medicijn.value = medicijn
    }

}
