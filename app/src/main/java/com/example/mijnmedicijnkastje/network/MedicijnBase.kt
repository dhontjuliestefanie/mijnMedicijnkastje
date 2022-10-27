package com.example.mijnmedicijnkastje.network

import com.squareup.moshi.Json

data class MedicijnBase(

    @Json(name = "get") val method : String,
    val _id: Int,
    val registratienummer: String,
    val productnaam: String,
    val productnaam_link: String,
    val aTC: String,
    val werkzame_stof: String
)