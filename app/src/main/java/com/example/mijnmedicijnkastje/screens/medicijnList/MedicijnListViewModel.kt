package com.example.mijnmedicijnkastje.screens.medicijnList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import java.text.SimpleDateFormat
import java.util.*

class MedicijnListViewModel : ViewModel() {

    val today = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = formatter.format(today)

    private val medicijn2 = Medicijn(
        "Nurofen voor Kinderen Sinaasappel 100 mg, kauwcapsules, zacht",
        "RVG 112524",
        "www.nurofen.be",
        formattedDate,
        20,
        null
    )

    private var _medicijn = MutableLiveData<Medicijn>()
    val medicijn: LiveData<Medicijn>
        get() {
            return _medicijn
        }

    private val _naam = MutableLiveData<String>()
    val naam: LiveData<String>
        get() = _naam

    private val _registratienummer = MutableLiveData<String>()
    val registratienummer: LiveData<String>
        get() = _registratienummer

    private val _linkInfo = MutableLiveData<String>()
    val linkInfo: LiveData<String>
        get() = _linkInfo

    private var _houdbaarheidsdatum = MutableLiveData<String>()
    val houdbaarheidsdatum: LiveData<String>
        get() = _houdbaarheidsdatum

    private var _aantal = MutableLiveData<Int>()
    val aantal: LiveData<Int>
        get() = _aantal

    private var _extraInfo = MutableLiveData<String?>()
    val extraInfo: LiveData<String?>
        get() = _extraInfo

    init {
        _naam.value = this.medicijn2.naam
        _registratienummer.value = this.medicijn2.registratienr
        _linkInfo.value = this.medicijn2.linkInfo
        _houdbaarheidsdatum.value = this.medicijn2.houdbaarheidsdatum
        _aantal.value = this.medicijn2.aantal
        _extraInfo.value = this.medicijn2.extraInfo
        _medicijn.value = this.medicijn2
    }

    private val _navigateToMedicijnDetail = MutableLiveData<Boolean>()
    val navigateToMedicijnDetail : LiveData<Boolean>
        get() {return _navigateToMedicijnDetail}

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
    val createMedicijn : LiveData<Boolean>
        get() {return _createMedicijn}

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