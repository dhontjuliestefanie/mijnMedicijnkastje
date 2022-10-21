package com.example.mijnmedicijnkastje.screens.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mijnmedicijnkastje.models.MockupUserDB
import com.example.mijnmedicijnkastje.models.Persoon

class UserViewModel : ViewModel() {
//    private val persoon = Persoon(
//        "Dhont",
//        "Julie",
//        "03/07/1986",
//        "Medicijn 1 \n iedere dag voor het ontbijt  \n" +
//                " Medicijn 2 \n" +
//                " iedere avond"
//    )
//
//    private val _naam = MutableLiveData<String>()
//    val naam: LiveData<String>
//        get() = _naam
//
//    private val _voornaam = MutableLiveData<String>()
//    val voornaam: LiveData<String>
//        get() = _voornaam
//
//    private val _geboortedatum = MutableLiveData<String>()
//    val geboortedatum: LiveData<String>
//        get() = _geboortedatum
//
//
//    private val _dagMedicatie = MutableLiveData<String?>()
//    val dagMedicatie: MutableLiveData<String?>
//        get() = _dagMedicatie

    private val _persoon = MutableLiveData<Persoon?>()
    val persoon: MutableLiveData<Persoon?>
        get() = _persoon

    init {
//        _naam.value = this.persoon.naam
//        _voornaam.value = this.persoon.voornaam
//        _geboortedatum.value = this.persoon.geboortedatum
//        _dagMedicatie.value = this.persoon.dagelijkseMedicatie
        _persoon.value = MockupUserDB().getUser()

    }

}