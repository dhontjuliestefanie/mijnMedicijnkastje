package com.example.mijnmedicijnkastje.screens.medicijnkast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import java.text.SimpleDateFormat
import java.util.*

class MedicijnkastViewModel : ViewModel() {

    val today = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = formatter.format(today)

    private val medicijn = Medicijn(
        "Nurofen voor Kinderen Sinaasappel 100 mg, kauwcapsules, zacht",
        "RVG 112524",
        "www.nurofen.be",
        formattedDate,
        20,
        null
    )


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
        _naam.value = this.medicijn.naam
        _registratienummer.value = this.medicijn.registratienr
        _linkInfo.value = this.medicijn.linkInfo
        _houdbaarheidsdatum.value = this.medicijn.houdbaarheidsdatum
        _aantal.value = this.medicijn.aantal
        _extraInfo.value = this.medicijn.extraInfo
    }

    fun increaseDosis() {
        _aantal.value = _aantal.value?.plus(1)
    }

    fun decreaseDosis() {
        if (_aantal.value!! > 0) {
            _aantal.value = aantal.value?.plus(-1)
        } else {
            _aantal.value = 0
        }
    }

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
}
