package com.example.mijnmedicijnkastje.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserModelFactory  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}