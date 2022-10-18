package com.example.mijnmedicijnkastje.models

import android.util.Log

data class Persoon(var naam: String, var voornaam: String, var geboortedatum: String, var dagelijkseMedicatie: String?) {
    init {
        Log.i("Persoon", "Persoon created")
    }
}