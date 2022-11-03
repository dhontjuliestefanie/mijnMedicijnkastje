package com.example.mijnmedicijnkastje.screens.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mijnmedicijnkastje.database.User
import com.example.mijnmedicijnkastje.database.UserDatabaseDAO
import kotlinx.coroutines.launch

class UserViewModel(val database: UserDatabaseDAO, application: Application) :
    AndroidViewModel(application) {

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?>
        get() = _user

    private var _addUserClicked = MutableLiveData<Boolean>()
    val addUserClicked: LiveData<Boolean>
        get() {
            return _addUserClicked
        }

    private var _users = database.getAllUsers()
    val users: LiveData<List<User>>
        get() {
            return _users
        }

    init {
        _addUserClicked.value = false
    }

    fun clickUser(user: User) {
        _user.value = user
    }

    fun btnAddUserClicked() {
        _addUserClicked.value = true
    }

    fun addUser(naam: String, voornaam: String, geboortedatum: String) {
        val user = User(null, naam, voornaam, geboortedatum)
        viewModelScope.launch {
            database.insert(user)
        }
        _user.value = user
    }

    fun removeUser() {
        viewModelScope.launch {
            user.value?.let { database.delete(it) }
        }
        _user.value = null
    }

}
