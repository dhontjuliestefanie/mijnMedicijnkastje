package com.example.mijnmedicijnkastje.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainModelFactory  : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}