package com.example.mijnmedicijnkastje.models

class MockupUserDB {
    fun getUser(): Persoon {
        return Persoon(
            "Dhont",
            "Julie",
            "03/07/1986",
            "Medicijn 1 \n iedere dag voor het ontbijt  \n" +
                    " Medicijn 2 \n" +
                    " iedere avond"
        )
    }
}