package com.example.mijnmedicijnkastje.screens.medicijnList

import MedicijnBase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.Medicijn
import com.example.mijnmedicijnkastje.models.MockupMedicijnDB
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

    private val _medicijnen = MockupMedicijnDB().getMedicijnen()
    val medicijnen: LiveData<List<Medicijn>>
        get() {
            return _medicijnen
        }

    init {
        _medicijn.value = null
    }

    fun clickMedicijn(medicijn: Medicijn) {
        _medicijn.value = medicijn
    }

    fun onMedicijnClicked(medicijn: Medicijn) {
        _medicijn.value = medicijn
    }

    fun onMedicijnDetailNavigated() {
        _medicijn.value = null
    }

    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    init {
        getMedicijnProperties()
    }

    private fun getMedicijnProperties() {
        MedicijnAPI.retrofitService.getProperties().enqueue(object : Callback<MedicijnBase> {
            override fun onResponse(call: Call<MedicijnBase>, response: Response<MedicijnBase>) {
                _response.value =
                    "${response.body()?.result?.records?.size} Medicijn properties retrieved"
                _response.value = response.body()?.result?.records.toString()
            }

            override fun onFailure(call: Call<MedicijnBase>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
        })
    }

    private var _medicijnBase = MutableLiveData<MedicijnBase?>()
    val medicijnBase: LiveData<MedicijnBase?>
        get() {
            return _medicijnBase
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

//    private val _medicijnen = MutableLiveData<List<Medicijn>>()
//    val medicijnen: LiveData<List<Medicijn>>
//        get() {
//            return _medicijnen
//        }

//    fun getMedicijnProperties() {
//
//        viewModelScope.launch {
//            try {
//                _medicijnBase.value = MedicijnAPI.retrofitService.getProperties()
//                val meds = _medicijnBase.value!!.result.records
//                _response.value = meds.toString()
//            } catch (e: Exception) {
//                _response.value = "oeps"
//            }
//        }
//    }


//    private fun getMeds() {
////        viewModelScope.launch {
////            try {
////                val listResult = MedicijnAPI.retrofitService.getMedicijnByName("Nurofen")
////                _response.value = "Success: ${listResult} properties retrieved"
////            } catch (e: Exception) {
////                _response.value = "Failure: ${e.message}"
////            }
////
////        }
////    }
//        MedicijnAPI.retrofitService.getProperties().enqueue(
//            object : Callback<List<Records>> {
//                 override fun onResponse(call: Call<List<Records>>, response: Response<List<Records>>) {
//                    _response.value = "Success: ${response.body()?.toString()} properties received"
//                }
//
//                override fun onFailure(call: Call<List<Records>>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                }
//            })
//    }
//    private var _medicijnBase = MutableLiveData<MedicijnBase?>()
//    val medicijnBase: LiveData<MedicijnBase?>
//        get() {
//            return _medicijnBase
//        }
//
//    init {
//        viewModelScope.launch {
//            try {
//                _medicijnBase.value = MedicijnAPI.retrofitService.getMedicijnByName("Nurofen")
//
//            } catch (e: Exception) {
//            }
//        }
//    }

//    init {
//        var medicijn = medicijnBase.value?.result?.records?.toString()
//        Log.i("medlstvm", medicijn.toString())
////        _response.value = medicijnBase.toString()
//        _response.value = medicijn.toString()
//
//    }

// navigatie

    private val _navigateToMedicijnDetail = MutableLiveData<Boolean>()
    val navigateToMedicijnDetail: LiveData<Boolean>
        get() {
            return _navigateToMedicijnDetail
        }

    init {
        _navigateToMedicijnDetail.value = false
    }

    fun btnNavigateToMedicijnDetailClicked() {
        _navigateToMedicijnDetail.value = true
    }

    fun navigateToMedicijnDetailFinished() {
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
