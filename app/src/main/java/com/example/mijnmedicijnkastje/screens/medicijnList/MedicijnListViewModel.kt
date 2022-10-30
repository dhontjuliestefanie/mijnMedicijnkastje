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

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() {
            return _error
        }

    private var _loadingFinished = MutableLiveData<Boolean>()
    val loadingFinished: LiveData<Boolean>
        get() {
            return _loadingFinished
        }

    init {
        _error.value = ""
        _loadingFinished.value = false
        getMedicijnProperties()
    }


    private fun getMedicijnProperties() {
        MedicijnAPI.retrofitService.getProperties().enqueue(object : Callback<MedicijnBase> {
            override fun onResponse(call: Call<MedicijnBase>, response: Response<MedicijnBase>) {
                _response.value =
                    "${response.body()?.result?.records?.size} Medicijn properties retrieved"
                val recordlist = response.body()?.result?.records
                _alleMedicijnen.value = recordlist?.map {
                    Medicijn(
                        it.productnaam,
                        it.registratienummer,
                        it.productnaam_link,
                        null,
                        20,
                        null
                    )
                }
                _medicijnen.value = alleMedicijnen.value?.subList(0,100)
                _loadingFinished.value = true
            }

            override fun onFailure(call: Call<MedicijnBase>, t: Throwable) {
                _error.value = "Failure: " + t.message
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
        var gefilterdeLijst = _alleMedicijnen.value?.filter { medicijn -> medicijn.naam.lowercase().contains(zoekterm.lowercase()) }
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
