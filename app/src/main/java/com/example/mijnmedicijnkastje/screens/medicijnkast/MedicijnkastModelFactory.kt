package com.example.mijnmedicijnkastje.screens.medicijnkast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MedicijnkastModelFactory  : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicijnkastViewModel::class.java)) {
            return MedicijnkastViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}