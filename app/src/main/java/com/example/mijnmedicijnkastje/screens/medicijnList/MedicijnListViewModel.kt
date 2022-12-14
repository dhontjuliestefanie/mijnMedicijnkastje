package com.example.mijnmedicijnkastje.screens.medicijnList

import MedicijnBase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import com.example.mijnmedicijnkastje.network.MedicijnAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue


class MedicijnListViewModel : ViewModel() {

    private var _medicijn = MutableLiveData<Medicijn?>()
    val medicijn: MutableLiveData<Medicijn?>
        get() {
            return _medicijn
        }

    private val _medicijnen = MutableLiveData<List<Medicijn>?>()
    val medicijnen: MutableLiveData<List<Medicijn>?>
        get() {
            return _medicijnen
        }

    private val _alleMedicijnen = MutableLiveData<List<Medicijn>?>()
    val alleMedicijnen: MutableLiveData<List<Medicijn>?>
        get() {
            return _alleMedicijnen
        }

    init {
        _medicijn.value = null
    }

    fun clickMedicijn(medicijn: Medicijn) {
        _medicijn.value = medicijn
    }

    fun onMedicijnDetailNavigated() {
        _medicijn.value = null
    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() {
            return _response
        }

    private var _info = MutableLiveData<String>()
    val info: LiveData<String>
        get() {
            return _info
        }

    private var _loadingFinished = MutableLiveData<Boolean>()
    val loadingFinished: LiveData<Boolean>
        get() {
            return _loadingFinished
        }

    init {
        _info.value = ""
        _loadingFinished.value = false
        _medicijnen.value = null
        getMedicijnProperties()
    }

    private fun getToday(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun getMedicijnProperties() {
        MedicijnAPI.retrofitService.getProperties().enqueue(object : Callback<MedicijnBase> {
            val today = getToday()
            override fun onResponse(call: Call<MedicijnBase>, response: Response<MedicijnBase>) {
                val recordlist = response.body()?.result?.records
                _alleMedicijnen.value = recordlist?.map {
                    Medicijn(
                        it.productnaam,
                        it.registratienummer,
                        it.productnaam_link,
                        today,
                        20,
                        null
                    )
                }
                _loadingFinished.value = true
            }

            override fun onFailure(call: Call<MedicijnBase>, t: Throwable) {
                _info.value = "Failure: " + t.message
                _loadingFinished.value = true
            }
        })
    }

    private var _medicijnBase = MutableLiveData<MedicijnBase?>()
    val medicijnBase: LiveData<MedicijnBase?>
        get() {
            return _medicijnBase
        }

    fun getFilterdeLijst(zoekterm: String): List<Medicijn>? {
        var gefilterdeLijst = _alleMedicijnen.value?.filter { medicijn ->
            medicijn.naam.lowercase().contains(zoekterm.lowercase())
        }
        var aantalMeds = gefilterdeLijst?.size
        if (aantalMeds?.absoluteValue!! > 0) {
            _info.value = "${aantalMeds} medicijnen gevonden."
        }
        else {
            _info.value = "Geen medicijn gevonden met de naam ${zoekterm}. \n Wil je zelf een medicijn aanmaken? \n Druk daarvoor op de roze knop."
        }

        return gefilterdeLijst
    }


// navigatie

    private val _navigateToMedicijnDetail = MutableLiveData<Boolean>()
    val navigateToMedicijnDetail: LiveData<Boolean>
        get() {
            return _navigateToMedicijnDetail
        }

    init {
        _navigateToMedicijnDetail.value = false
    }

    private val _createMedicijn = MutableLiveData<Boolean>()
    val createMedicijn: LiveData<Boolean>
        get() {
            return _createMedicijn
        }

    init {
        _createMedicijn.value = false
    }

    fun btnCreateMedicijnClicked() {
        _createMedicijn.value = true
    }

    fun createMedicijnFinished() {
        _createMedicijn.value = false
    }
}
