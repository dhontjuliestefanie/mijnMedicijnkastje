package com.example.mijnmedicijnkastje.screens.medicijn

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import java.text.SimpleDateFormat
import java.util.*

class MedicijnViewModel() : ViewModel() {
    val today = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = formatter.format(today)

    private var _medicijn = MutableLiveData<Medicijn>()
    val medicijn: LiveData<Medicijn>
        get() {
            return _medicijn
        }

    private val medicijn2 = Medicijn(
        "Nurofen voor Kinderen Sinaasappel 100 mg, kauwcapsules, zacht",
        "RVG 112524",
        "www.nurofen.be",
        formattedDate,
        20,
        null
    )

    private val _naam = MutableLiveData<String>()
    val naam: LiveData<String>
        get() = _naam

    private val _registratienummer = MutableLiveData<String>()
    val registratienummer: LiveData<String>
        get() = _registratienummer

    private val _linkInfo = MutableLiveData<String>()
    val linkInfo: LiveData<String>
        get() = _linkInfo

    private var _houdbaarheidsdatum = MutableLiveData<String>()
    val houdbaarheidsdatum: LiveData<String>
        get() = _houdbaarheidsdatum

    private var _aantal = MutableLiveData<Int>()
    val aantal: LiveData<Int>
        get() = _aantal

    private var _extraInfo = MutableLiveData<String?>()
    val extraInfo: LiveData<String?>
        get() = _extraInfo

    init {
        _naam.value = this.medicijn2.naam
        _registratienummer.value = this.medicijn2.registratienr
        _linkInfo.value = this.medicijn2.linkInfo
        _houdbaarheidsdatum.value = this.medicijn2.houdbaarheidsdatum
        _aantal.value = this.medicijn2.aantal
        _extraInfo.value = this.medicijn2.extraInfo
//        _medicijn.value = __medicijn
    }

    fun increaseDosis() {
        _aantal.value = _aantal.value?.plus(1)
    }

    fun decreaseDosis() {
        if (_aantal.value!! > 0) {
            _aantal.value = aantal.value?.plus(-1)
        }
        else {
            _aantal.value = 0
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


