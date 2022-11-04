package com.example.mijnmedicijnkastje.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "dag_med_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["idUser"],
        onDelete = ForeignKey.CASCADE
    )],
)

data class DagelijkseMedicatie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "idUser")
    var idUser: Int? = null,

    @ColumnInfo(name = "naamMed")
    var naamMed: String? = null,

    @ColumnInfo(name = "tijdstip")
    var tijdstip: String? = null,
) : Parcelable