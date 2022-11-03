package com.example.mijnmedicijnkastje.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null,

    @ColumnInfo(name = "naam")
    var naam: String? = null,

    @ColumnInfo(name = "voornaam")
    var voornaam: String? = null,

    @ColumnInfo(name = "geboortedatum")
    var geboortedatum: String? = null,

    ) : Parcelable