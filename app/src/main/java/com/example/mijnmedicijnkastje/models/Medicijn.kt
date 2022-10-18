package com.example.mijnmedicijnkastje.models

import android.util.Log
data class Medicijn(var naam: String, var registratienr: String, var linkInfo: String, var houdbaarheidsdatum: String, var aantal: Int, var extraInfo: String?) {
    init {
        Log.i("Medicijn", "Medicijn created")
    }
}