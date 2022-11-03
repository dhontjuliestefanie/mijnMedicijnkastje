package com.example.mijnmedicijnkastje.models

import androidx.lifecycle.MutableLiveData
import com.example.mijnmedicijnkastje.database.User

class MockupUserDB {
    val user1 : User = User(1, "Dhont", "Julie", "03/07/1986")
    val user2 : User = User(2, "Devisscher", "Kristof", "08/01/1980")
    val user3 : User = User(3, "Devisscher", "Lou", "07/06/2013")
    val user4 : User = User(4, "Devisscher", "Max", "23/04/2018")

    fun getUser(): Persoon {
        return Persoon(
            "Dhont",
            "Julie",
            "03/07/1986",
            1
        )
    }

    private var users: Array<User> = arrayOf(user1, user2, user3, user4)

    fun getUsers(): MutableLiveData<List<User>> {
        var usersLst = MutableLiveData<List<User>>()
        usersLst.value = users.toList()
        return usersLst
    }

    fun findUser(userNaam: String): User {
        val user = users.filter { user -> user.voornaam.equals(userNaam) }
        return user[0]
    }
}