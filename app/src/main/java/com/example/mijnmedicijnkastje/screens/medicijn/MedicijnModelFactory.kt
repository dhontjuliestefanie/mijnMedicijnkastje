
package com.example.mijnmedicijnkastje.screens.medicijn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mijnmedicijnkastje.models.Medicijn

class MedicijnModelFactory(private val medicijn: Medicijn)  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicijnViewModel::class.java)) {
            return MedicijnViewModel(medicijn) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}