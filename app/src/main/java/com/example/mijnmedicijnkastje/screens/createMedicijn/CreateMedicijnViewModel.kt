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
    var aantal = MutableLiveData<Int>()
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
        aantal.value = 0
        linkInfo.value = ""
        extraInfo.value = ""
    }


    fun btnNavigateToMedicijnKastClicked() {
        _voegToeAanMedicijnkast.value = true
        maakNieuwMedicijnAan()
        _medicijnVoorKast.value?.let { insert() }
    }

    fun maakNieuwMedicijnAan() {
        val newMedicijnInKast = MedicijnInKast()
        newMedicijnInKast.naam = naamMedicijn.value
        newMedicijnInKast.registratienr = registratienummer.value
        newMedicijnInKast.aantal = aantal.value!!
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