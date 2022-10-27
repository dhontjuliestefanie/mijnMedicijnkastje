package com.example.mijnmedicijnkastje.screens.createMedicijn

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.models.Medicijn
import com.example.mijnmedicijnkastje.screens.medicijn.MedicijnViewModel

class CreateMedicijnModelFactory(private val dataSource: MedicijnDatabaseDAO, private val applicaton: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateMedicijnViewModel::class.java)) {
            return CreateMedicijnViewModel(dataSource, applicaton) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
