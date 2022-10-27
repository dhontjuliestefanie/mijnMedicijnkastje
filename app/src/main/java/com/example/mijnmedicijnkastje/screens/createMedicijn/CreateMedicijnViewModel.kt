package com.example.mijnmedicijnkastje.screens.createMedicijn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import kotlinx.coroutines.launch

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

    fun btnNavigateToMedicijnKastClicked() {
        maakNieuwMedicijnAan()
        _medicijnVoorKast.value?.let { insert() }
        _voegToeAanMedicijnkast.value = true

    }

    fun maakNieuwMedicijnAan() {
        val newMedicijnInKast = MedicijnInKast()
        newMedicijnInKast.naam = "Julie"
        newMedicijnInKast.registratienr = "1234"
        newMedicijnInKast.aantal = 30
        newMedicijnInKast.houdbaarheidsdatum = "03/07/1986"
        newMedicijnInKast.linkInfo = "www.julie.be"
        newMedicijnInKast.extraInfo = "iedere dag om 18u een pilleke"
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


}