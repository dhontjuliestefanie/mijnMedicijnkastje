package com.example.mijnmedicijnkastje.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mijnmedicijnkastje.database.DagelijkseMedicatie
import com.example.mijnmedicijnkastje.database.User

data class DagMedAndUser(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "idUser"
    )
    val dagelijkseMedicatie: List<DagelijkseMedicatie>
)
