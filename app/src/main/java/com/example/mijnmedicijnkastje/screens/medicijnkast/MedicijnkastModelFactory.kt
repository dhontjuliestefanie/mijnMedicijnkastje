package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO


class MedicijnkastModelFactory(private val dataSource: MedicijnDatabaseDAO, private val application: Application) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicijnkastViewModel::class.java)) {
            return MedicijnkastViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

