package com.example.mijnmedicijnkastje.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _navigateToMedicijnLijst = MutableLiveData<Boolean>()
    val navigateToMedicijnLijst: LiveData<Boolean>
        get() {
            return _navigateToMedicijnLijst
        }

    init {
        _navigateToMedicijnLijst.value = false
    }

    fun btnNavigateToMedicijnLijstClicked() {
        _navigateToMedicijnLijst.value = true
    }

    fun navigateToMedicijnLijstFinished() {
        _navigateToMedicijnLijst.value = false
    }

    private val _navigateToMedicijnkast = MutableLiveData<Boolean>()
    val navigateToMedicijnkast: LiveData<Boolean>
        get() {
            return _navigateToMedicijnkast
        }

    init {
        _navigateToMedicijnkast.value = false
    }

    fun btnNavigateToMedicijnkastClicked() {
        _navigateToMedicijnkast.value = true
    }

    fun navigateToMedicijnkastFinished() {
        _navigateToMedicijnkast.value = false
    }

}