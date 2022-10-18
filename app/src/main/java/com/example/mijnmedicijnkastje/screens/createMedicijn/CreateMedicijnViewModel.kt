package com.example.mijnmedicijnkastje.screens.createMedicijn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateMedicijnViewModel : ViewModel() {

    private val _voegToeAanMedicijnkast = MutableLiveData<Boolean>()
    val voegToeAanMedicijnkast : LiveData<Boolean>
        get() {return _voegToeAanMedicijnkast}

    init {
        _voegToeAanMedicijnkast.value = false
    }

    fun btnNavigateToMedicijnKastClicked() {
        _voegToeAanMedicijnkast.value = true
    }

    fun navigateToMedicijnKastFinished() {
        _voegToeAanMedicijnkast.value = false
    }

}