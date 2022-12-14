package com.example.mijnmedicijnkastje.screens.medicijn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import com.example.mijnmedicijnkastje.models.Medicijn
import kotlinx.coroutines.launch

class MedicijnViewModel(
    __medicijn: Medicijn,
    val database: MedicijnDatabaseDAO,
    application: Application
) : AndroidViewModel(application) {
    private var _medicijn = MutableLiveData<Medicijn>()
    val medicijn: LiveData<Medicijn>
        get() {
            return _medicijn
        }
    var aantal = MutableLiveData<Int?>()

    init {
        _medicijn.value = __medicijn
        aantal.value = __medicijn.aantal
    }

    fun increaseDosis() {
        aantal.value = aantal.value!!.plus(1)
    }

    fun decreaseDosis() {
        if (aantal.value!! > 0) {
            aantal.value = aantal.value!!.plus(-1)
        } else {
            aantal.value = 0
        }
    }

    private var _navigateToMedicijnkast = MutableLiveData<Boolean>()
    val navigateToMedicijnkast: LiveData<Boolean>
        get() {
            return _navigateToMedicijnkast
        }

    init {
        _navigateToMedicijnkast.value = false
    }

    fun btnNavigateToMedicijnKastClicked() {
        voegMedicijnToeAanKast()
        _navigateToMedicijnkast.value = true
    }

    fun navigateToMedicijnKastFinished() {
        _navigateToMedicijnkast.value = false
    }

    var houdbaarheidsdatum = MutableLiveData<String>()

    fun voegMedicijnToeAanKast() {
        val newMedicijnInKast = MedicijnInKast()
        newMedicijnInKast.naam = medicijn.value?.naam
        newMedicijnInKast.registratienr = medicijn.value?.registratienr
        newMedicijnInKast.aantal = aantal.value!!
        newMedicijnInKast.houdbaarheidsdatum = medicijn.value?.houdbaarheidsdatum
        newMedicijnInKast.linkInfo = medicijn.value?.linkInfo
        newMedicijnInKast.extraInfo = medicijn.value?.extraInfo
        viewModelScope.launch {
            database.insert(newMedicijnInKast)
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

    private var _linkToWebsite: MutableLiveData<Boolean> = MutableLiveData()
    val linkToWebsite: LiveData<Boolean>
        get() {
            return _linkToWebsite
        }

    init {
        _linkToWebsite.value = false
    }

    fun btnMeerInfoEnBijsluiterClicked() {
        _linkToWebsite.value = true
    }

    fun btnMeerInfoEnBijsluiterClickFinished() {
        _linkToWebsite.value = false
    }

    private var _aantalInMedKast: MutableLiveData<String> = MutableLiveData()
    val aantalInMedKast: LiveData<String>
        get() {
            return _aantalInMedKast
        }

    private var _houdbaarheidsdatumAlInKast: MutableLiveData<String> = MutableLiveData()
    val houdbaarheidsdatumAlInKast: LiveData<String>
        get() {
            return _houdbaarheidsdatumAlInKast
        }

    private val _medInKast = MutableLiveData<Boolean>()
    val medInKast: LiveData<Boolean>
        get() {
            return _medInKast
        }

    init {
        _aantalInMedKast.value = ""
        _houdbaarheidsdatumAlInKast.value = ""
        _medInKast.value = true
        medAlInKast()
    }

    fun medAlInKast() {
        viewModelScope.launch {
            val alInKast = medicijn.value?.let { database.get(it.naam) }
            val aantalInKast = alInKast?.aantal
            val datumInKast = alInKast?.houdbaarheidsdatum
            if (alInKast != null) {
                _medInKast.value = false
            }
            _aantalInMedKast.value = "U heb nog ${aantalInKast} dosissen in uw medicijnkastje."
            _houdbaarheidsdatumAlInKast.value = "Houdbaarheidsdatum: ${datumInKast}"
        }
    }

}


