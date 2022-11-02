package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.MedicijnDatabaseDAO
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MedicijnkastViewModel(val database: MedicijnDatabaseDAO, application: Application) :
    AndroidViewModel(application) {

    private var _medicijnkast = database.getAllMedicijnen()

    val medicijnkast: LiveData<List<MedicijnInKast>>
        get() {
            return _medicijnkast
        }

    private var _medicijn = MutableLiveData<MedicijnInKast?>()
    val medicijn: MutableLiveData<MedicijnInKast?>
        get() {
            return _medicijn
        }

    private var _aantal = MutableLiveData<Int?>()
    val aantal: MutableLiveData<Int?>
        get() {
            return _aantal
        }

    init {
        _medicijn.value = null
        _aantal.value = 0
    }

    fun clickMedicijn(medicijn: MedicijnInKast) {
        _medicijn.value = medicijn
        _aantal.value = medicijn.aantal
    }

    fun delete() {
        viewModelScope.launch {
            _medicijn.value?.let { database.delete(it) }
        }
    }

    fun update() {
        viewModelScope.launch {
            database.update(medicijn.value!!)
        }
    }

    fun getFilterdeLijst(zoekterm: String, currentList: MutableList<MedicijnInKast>): List<MedicijnInKast>? {
        var gefilterdeLijst = currentList.filter { medicijnInKast ->
            medicijnInKast.naam!!.lowercase().contains(zoekterm.lowercase())
        }
        return gefilterdeLijst
    }

    private var _switchActive = MutableLiveData<Boolean>()
    val switchActive: MutableLiveData<Boolean>
        get() {
            return _switchActive
        }

    private val _activeText = MutableLiveData<String>()
    private val _notActiveText = MutableLiveData<String>()

    var switchText = MutableLiveData<String>()

    init {
        switchActive.value = false
        _activeText.value = "Toon alle medicijnen"
        _notActiveText.value = "Toon enkel vervallen medicijnen"
        switchText.value = getActiveText()
    }

    fun onActiveChanged() {
        switchText.value = getActiveText()
    }

    private fun getActiveText(): String {
        var text = _notActiveText
        if (switchActive.value == true) {
            text = _activeText
        }
        return text.value!!
    }

    private fun getToday(): String {
        val today = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    fun getLijstVervallenProducten(currentList: MutableList<MedicijnInKast>): List<MedicijnInKast>? {
        var today = getToday()
        var gefilterdeLijst = currentList.filter { medicijnInKast -> medicijnInKast.houdbaarheidsdatum!! < today}
        return gefilterdeLijst
    }
}