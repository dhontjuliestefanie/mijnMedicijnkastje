package com.example.mijnmedicijnkastje.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicijnen_in_kast_table")
data class MedicijnInKast(
    @PrimaryKey(autoGenerate = true)
    var medId: Int? = null,

    @ColumnInfo(name = "naam")
    var naam: String? = null,

    @ColumnInfo(name = "registratienummer")
    var registratienr: String? = null,

    @ColumnInfo(name = "link_info")
    var linkInfo: String? = null,

    @ColumnInfo(name = "houdbaarheidsdatum")
    var houdbaarheidsdatum: String? = null,

    @ColumnInfo(name = "aantal")
    var aantal: Int = 20,

    @ColumnInfo(name = "extraInfo")
    var extraInfo: String? = null,

    @ColumnInfo(name = "dagelijkseMedicatie")
    var dagelijkseMedicatie: Boolean = false
)