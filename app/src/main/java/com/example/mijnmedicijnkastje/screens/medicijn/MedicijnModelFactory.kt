package com.example.mijnmedicijnkastje.screens.medicijn

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.models.Medicijn

class MedicijnModelFactory(
    private val medicijn: Medicijn,
    private val dataSource: MedicijnDatabaseDAO,
    private val applicaton: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicijnViewModel::class.java)) {
            return MedicijnViewModel(medicijn, dataSource, applicaton) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}