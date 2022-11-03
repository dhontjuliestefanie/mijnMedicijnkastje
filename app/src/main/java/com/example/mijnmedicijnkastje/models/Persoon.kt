package com.example.mijnmedicijnkastje.models

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize


@Parcelize
class Persoon(var naam: String, var voornaam: String, var geboortedatum: String, var id: Int):Parcelable {
    init {
        Log.i("Persoon", "Persoon created")
    }
}