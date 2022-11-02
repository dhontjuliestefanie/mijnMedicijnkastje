package com.example.mijnmedicijnkastje.screens.createMedicijn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateMedicijnViewModel(val database: MedicijnDatabaseDAO, application: Application) :
    AndroidViewModel(application) {

    private val _voegToeAanMedicijnkast = MutableLiveData<Boolean>()
    val voegToeAanMedicijnkast: LiveData<Boolean>
        get() {
            return _voegToeAanMedicijnkast
        }

    private val _medicijnVoorKast = MutableLiveData<MedicijnInKast>()
    val medicijnVoorKast: LiveData<MedicijnInKast>
        get() {
            return _medicijnVoorKast
        }

    init {
        _voegToeAanMedicijnkast.value = false
    }

    var naamMedicijn = MutableLiveData<String>()
    var registratienummer = MutableLiveData<String>()
    var aantal = MutableLiveData<String>()
    var houdbaarheidsdatum = MutableLiveData<String>()
    var linkInfo = MutableLiveData<String>()
    var extraInfo = MutableLiveData<String>()

    val today = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = formatter.format(today)

    init {
        naamMedicijn.value = ""
        registratienummer.value = ""
        houdbaarheidsdatum.value = formattedDate
        aantal.value = ""
        linkInfo.value = ""
        extraInfo.value = ""
    }

    private var _errorNaam = MutableLiveData<String>()
    val errorNaam: LiveData<String>
        get() {
            return _errorNaam
        }

    private var _errorAantal = MutableLiveData<String>()
    val errorAantal: LiveData<String>
        get() {
            return _errorAantal
        }


    private val _naamIngegeven = MutableLiveData<Boolean>()
    val naamIngegeven: LiveData<Boolean>
        get() {
            return _naamIngegeven
        }

    private val _aantalIngegeven = MutableLiveData<Boolean>()
    val aantalIngegeven: LiveData<Boolean>
        get() {
            return _aantalIngegeven
        }

    init {
        _naamIngegeven.value = true
        _aantalIngegeven.value = true
    }


    fun btnNavigateToMedicijnKastClicked() {
        if (naamMedicijn.value.isNullOrBlank()) {
            _naamIngegeven.value = false
            _errorNaam.value = "Naam van het medicijn is verplicht"
        } else if (Integer.parseInt(aantal.value) < 0) {
            _aantalIngegeven.value = false
            _errorAantal.value = "Gelieve een positief getal in te geven"
        } else {
            maakNieuwMedicijnAan()
            _medicijnVoorKast.value?.let { insert() }
            _voegToeAanMedicijnkast.value = true
        }
    }

    fun maakNieuwMedicijnAan() {
        val newMedicijnInKast = MedicijnInKast()
        newMedicijnInKast.naam = naamMedicijn.value
        newMedicijnInKast.registratienr = registratienummer.value
        newMedicijnInKast.aantal = Integer.parseInt(aantal.value)
        newMedicijnInKast.houdbaarheidsdatum = houdbaarheidsdatum.value
        newMedicijnInKast.linkInfo = linkInfo.value
        newMedicijnInKast.extraInfo = extraInfo.value
        _medicijnVoorKast.value = newMedicijnInKast
    }

    fun navigateToMedicijnKastFinished() {
        _voegToeAanMedicijnkast.value = false
    }

    fun insert() {
        viewModelScope.launch {
            _medicijnVoorKast.value?.let { database.insert(it) }
        }
    }

    private var _timePickerDialogData: MutableLiveData<Boolean> = MutableLiveData()
    val timePickerDialogData: LiveData<Boolean>
        get() {
            return _timePickerDialogData
        }

    init {
        _timePickerDialogData.value = false
    }

    fun btnCalendarDialogClick() {
        _timePickerDialogData.value = true
    }

    fun btnCalendarDialogClickFinished() {
        _timePickerDialogData.value = false
    }

}