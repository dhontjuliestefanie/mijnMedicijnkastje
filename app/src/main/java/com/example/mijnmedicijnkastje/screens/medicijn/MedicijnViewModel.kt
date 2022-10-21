package com.example.mijnmedicijnkastje.screens.medicijn

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import java.util.*

class MedicijnViewModel(__medicijn : Medicijn) : ViewModel() {
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

    private val _navigateToMedicijnkast = MutableLiveData<Boolean>()
    val navigateToMedicijnkast: LiveData<Boolean>
        get() {
            return _navigateToMedicijnkast
        }

    init {
        _navigateToMedicijnkast.value = false
    }

    fun btnNavigateToMedicijnKastClicked() {
        _navigateToMedicijnkast.value = true
    }

    fun navigateToMedicijnKastFinished() {
        _navigateToMedicijnkast.value = false
    }


}


