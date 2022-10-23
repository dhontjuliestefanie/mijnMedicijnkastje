package com.example.mijnmedicijnkastje.screens.medicijnList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import com.example.mijnmedicijnkastje.models.MockupMedicijnDB

class MedicijnListViewModel : ViewModel() {

    private var _medicijn = MutableLiveData<Medicijn?>()
    val medicijn: MutableLiveData<Medicijn?>
        get() {
            return _medicijn
        }

    private val _medicijnen = MockupMedicijnDB().getMedicijnen()
    val medicijnen: LiveData<List<Medicijn>>
        get() {
            return _medicijnen
        }

    init {
        _medicijn.value = null
    }

    fun clickMedicijn(medicijn : Medicijn) {
        _medicijn.value = medicijn
    }

    fun onMedicijnClicked(medicijn: Medicijn){
        _medicijn.value = medicijn
    }
    fun onMedicijnDetailNavigated() {
        _medicijn.value = null
    }




    // navigatie

    private val _navigateToMedicijnDetail = MutableLiveData<Boolean>()
    val navigateToMedicijnDetail: LiveData<Boolean>
        get() {
            return _navigateToMedicijnDetail
        }

    init {
        _navigateToMedicijnDetail.value = false
    }

    fun btnNavigateToMedicijnDetailClicked() {
        _navigateToMedicijnDetail.value = true
    }

    fun navigateToMedicijnDetailFinished() {
        _navigateToMedicijnDetail.value = false
    }

    private val _createMedicijn = MutableLiveData<Boolean>()
    val createMedicijn: LiveData<Boolean>
        get() {
            return _createMedicijn
        }

    init {
        _createMedicijn.value = false
    }

    fun btnCreateMedicijnClicked() {
        _createMedicijn.value = true
    }

    fun createMedicijnFinished() {
        _createMedicijn.value = false
    }
}