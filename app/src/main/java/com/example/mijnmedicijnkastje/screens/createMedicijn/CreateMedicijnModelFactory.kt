package com.example.mijnmedicijnkastje.screens.createMedicijn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mijnmedicijnkastje.screens.medicijn.MedicijnViewModel

class CreateMedicijnModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateMedicijnViewModel::class.java)) {
            return CreateMedicijnViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
