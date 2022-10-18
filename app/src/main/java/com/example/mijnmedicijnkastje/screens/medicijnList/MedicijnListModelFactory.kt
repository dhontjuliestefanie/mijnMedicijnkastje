package com.example.mijnmedicijnkastje.screens.medicijnList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MedicijnListModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicijnListViewModel::class.java)) {
            return MedicijnListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}