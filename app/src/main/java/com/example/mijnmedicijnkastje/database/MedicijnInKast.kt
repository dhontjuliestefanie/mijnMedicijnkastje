package com.example.mijnmedicijnkastje.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicijnen_in_kast_table")
data class MedicijnInKast(
    @PrimaryKey(autoGenerate = true)
    var medId: Int,

    @ColumnInfo(name = "naam")
    var naam: String,

    @ColumnInfo(name = "registratienummer")
    var registratienr: String,

    @ColumnInfo(name = "link_info")
    var linkInfo: String,

    @ColumnInfo(name = "houdbaarheidsdatum")
    var houdbaarheidsdatum: String,

    @ColumnInfo(name = "aantal")
    var aantal: Int,

    @ColumnInfo(name = "extraInfo")
    var extraInfo: String?
) {
}