package com.example.mijnmedicijnkastje.screens.medicijn

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.models.Medicijn
import java.util.*

class MedicijnViewModel(__medicijn : Medicijn, val database: MedicijnDatabaseDAO, application: Application) : AndroidViewModel(application) {
    private var _medicijn = MutableLiveData<Medicijn>()
    val medicijn: LiveData<Medicijn>
        get() {
            return _medicijn
        }
    var aantal = MutableLiveData<Int>()

    init {
        _medicijn.value = __medicijn
        aantal.value = __medicijn.aantal
    }

    fun increaseDosis() {
        aantal.value = aantal.value!!.plus(1)
    }

    fun decreaseDosis() {
        if (aantal.value!! > 0) {
            aantal.value = aantal.value!!.plus(-1)
        }
        else {
            aantal.value = 0
        }
    }


    private val _navigateToMedicijnkast = MutableLiveData<Boolean>()
    val navigateToMedicijnkast: LiveData<Boolean>
        get() {
            return _navigateToMedicijnkast
        }

    init {
        _navigateToMedicijnkast.value = false
    }


    fun btnNavigateToMedicijnKastClicked() {
//        onStartTracking()
        _navigateToMedicijnkast.value = true
    }

//    fun onStartTracking() {
//        viewModelScope.launch {
//            val newMedicijnInKast = MedicijnInKast()
//            newMedicijnInKast.naam = _medicijn.value?.naam
//            newMedicijnInKast.registratienr = medicijn.value?.registratienr
//            newMedicijnInKast.houdbaarheidsdatum = medicijn.value?.houdbaarheidsdatum
//            newMedicijnInKast.extraInfo = medicijn.value?.extraInfo
//            newMedicijnInKast.linkInfo = medicijn.value?.linkInfo
//            insert(newMedicijnInKast)
//        }
//    }
//
//    private suspend fun insert(medicijnInKast: MedicijnInKast) {
//        database.insert(medicijnInKast)
//    }

    fun navigateToMedicijnKastFinished() {
        _navigateToMedicijnkast.value = false
    }

    public fun showDatePickerDialog(mPickTimeBtn: Button, textView: TextView, context: Context) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    textView.setText("$dayOfMonth/${monthOfYear + 1}/$year")
                },
                year,
                month,
                day

            )
            dpd.show()
        }
    }


}


