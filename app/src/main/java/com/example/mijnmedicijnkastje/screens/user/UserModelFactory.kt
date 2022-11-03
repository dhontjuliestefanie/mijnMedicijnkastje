package com.example.mijnmedicijnkastje.screens.user

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.UserDatabaseDAO

class UserModelFactory(private val dataSource: UserDatabaseDAO, private val applicaton: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource, applicaton) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}