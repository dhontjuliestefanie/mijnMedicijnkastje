package com.example.mijnmedicijnkastje.models;

import java.text.SimpleDateFormat
import java.util.*

class MockupMedicijnDB {

    val today = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = formatter.format(today)

    fun getMedicijn(): Medicijn {
        return Medicijn(
            "Nurofen voor Kinderen Sinaasappel 100 mg, kauwcapsules, zacht",
            "RVG 112524",
            "www.nurofen.be",
            formattedDate,
            20,
            null
        )
    }
}

