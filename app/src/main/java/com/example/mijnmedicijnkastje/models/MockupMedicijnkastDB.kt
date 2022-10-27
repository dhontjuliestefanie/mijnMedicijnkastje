package com.example.mijnmedicijnkastje.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import java.text.SimpleDateFormat
import java.util.*

class MockupMedicijnkastDB {

    val today = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = formatter.format(today)


    private val med1 = MedicijnInKast(
        1,
        "Nurofen voor Kinderen Sinaasappel 100 mg, kauwcapsules, zacht",
        "RVG 112524",
        "www.nurofen.be",
        formattedDate,
        20,
        null,
        false
    )
    private val med2 = MedicijnInKast(
        2,
        "Brufen 400 mg bruisgranulaat",
        "RVG 110571",
        "www.brufen.be",
        formattedDate,
        20,
        null,
    false

    )
    private val med3 = MedicijnInKast(
        3,
        "Vicks Vaporub, zalf voor inhalatiedamp",
        "RVG 120425//03088",
        "www.vicks.be",
        formattedDate,
        20,
        null,
        true
    )
    private var medicijnen: Array<MedicijnInKast> = arrayOf(med1, med2, med3)

    fun getMedicijnen(): LiveData<List<MedicijnInKast>> {
        var medList = MutableLiveData<List<MedicijnInKast>>()
        medList.value = medicijnen.toList()
        return medList
    }

//    fun removeMedicijn(medicijn: MedicijnInKast) {
//        val result = medicijnen.toMutableList()
//        result.remove(medicijn)
//        medicijnen = result.toTypedArray()
//    }
}



