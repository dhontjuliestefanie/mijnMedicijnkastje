package com.example.mijnmedicijnkastje.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
            "http://www.nurofen.be",
            formattedDate,
            20,
            null
        )
    }

    private val med1 = Medicijn(
        "Nurofen voor Kinderen Sinaasappel 100 mg, kauwcapsules, zacht",
        "RVG 112524",
        "http://www.nurofen.be",
        formattedDate,
        20,
        null
    )
    private val med2 = Medicijn(
        "Brufen 400 mg bruisgranulaat",
        "RVG 110571",
        "http://www.brufen.be",
        formattedDate,
        20,
        null
    )
    private val med3 = Medicijn(
        "Vicks Vaporub, zalf voor inhalatiedamp",
        "RVG 120425//03088",
        "http://www.vicks.be",
        formattedDate,
        20,
        null
    )
    private var medicijnen: Array<Medicijn> = arrayOf(med1, med2, med3)

    fun getMedicijnen(): LiveData<List<Medicijn>> {
        val medList = MutableLiveData<List<Medicijn>>()
        medList.value = medicijnen.toList()
        return medList
    }

    fun removeMedicijn(medicijn: Medicijn) {
        val result = medicijnen.toMutableList()
        result.remove(medicijn)
        medicijnen = result.toTypedArray()
    }
}



