package com.example.mijnmedicijnkastje.models

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medicijn(var naam: String, var registratienr: String, var linkInfo: String?, var houdbaarheidsdatum: String?, var aantal: Int?, var extraInfo: String?):Parcelable {
    init {
        Log.i("Medicijn", "Medicijn created")
    }
}