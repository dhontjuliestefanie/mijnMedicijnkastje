package com.example.mijnmedicijnkastje.screens.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.DagelijkseMedicatie
import com.example.mijnmedicijnkastje.database.User
import com.example.mijnmedicijnkastje.database.UserDatabaseDAO
import kotlinx.coroutines.launch

class UserViewModel(val database: UserDatabaseDAO, application: Application) :
    AndroidViewModel(application) {

    private var _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?>
        get() = _user

    private var _addUserClicked = MutableLiveData<Boolean>()
    val addUserClicked: LiveData<Boolean>
        get() {
            return _addUserClicked
        }

    private var _addDagMedClicked = MutableLiveData<Boolean>()
    val addDagMedClicked: LiveData<Boolean>
        get() {
            return _addDagMedClicked
        }

    private var _userSelected = MutableLiveData<Boolean>()
    val userSelected: LiveData<Boolean>
        get() {
            return _userSelected
        }

    private var _users = database.getAllUsers()
    val users: LiveData<List<User>>
        get() {
            return _users
        }

    private var _dagMed = MutableLiveData<DagelijkseMedicatie?>()
    val dagmed: MutableLiveData<DagelijkseMedicatie?>
        get() = _dagMed

    private var _dagMedVanUser = MutableLiveData<List<DagelijkseMedicatie?>>()
    val dagMedVanUser: MutableLiveData<List<DagelijkseMedicatie?>>
        get() = _dagMedVanUser


    init {
        _addUserClicked.value = false
        _addDagMedClicked.value = false
        _user.value = null
        _dagMed.value = null
        if (user.value?.id == null) {
            _userSelected.value = false
        }
        getLstDagelijkseMedVanUser()
    }

    fun getLstDagelijkseMedVanUser() {
        viewModelScope.launch {
            if (user.value?.id != null) {
                _dagMedVanUser.value = user.value!!.id?.let { database.getDagMedAndUser(it) }!!
            } else {
                _dagMedVanUser.value = emptyList()
            }
        }
    }

    fun clickUser(user: User) {
        _user.value = user
        _userSelected.value = true
        getLstDagelijkseMedVanUser()
    }

    fun clickDagMed(dagelijkseMedicatie: DagelijkseMedicatie) {
        _dagMed.value = dagelijkseMedicatie
        Log.i("Julie", _dagMed.value!!.id.toString())
    }

    fun btnAddUserClicked() {
        _addUserClicked.value = true
    }

    fun btnAddDagMedClicked() {
        _addDagMedClicked.value = true
    }

    fun addUser(naam: String, voornaam: String, geboortedatum: String) {
        val user = User(null, naam, voornaam, geboortedatum)
        viewModelScope.launch {
            database.insert(user)
            _user.value = database.getUserByName(voornaam)
        }
        _userSelected.value = true
    }

    fun removeUser() {
        viewModelScope.launch {
            user.value?.let { database.delete(it) }
        }
        _user.value = null
        _dagMedVanUser.value = emptyList()
        _userSelected.value = false
    }

    fun addDagMedVanUser(naamMed: String, tijdstip: String) {
        val dagelijkseMedicatie = DagelijkseMedicatie(null, user.value?.id, naamMed, tijdstip)
        viewModelScope.launch {
            database.insertDagMed(dagelijkseMedicatie)
        }
        getLstDagelijkseMedVanUser()
    }

    fun removeDagMedVanUser() {
        viewModelScope.launch {
            dagmed.value?.let { database.deleteDagMed(it) }
        }
        getLstDagelijkseMedVanUser()
    }

}
