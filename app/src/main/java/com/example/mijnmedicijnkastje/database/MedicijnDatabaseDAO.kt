package com.example.mijnmedicijnkastje.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicijnDatabaseDAO {
    @Insert
    fun insert(medicijn: MedicijnInKast)

    @Update
    fun update(medicijn: MedicijnInKast)

    @Delete
    fun delete(medicijn: MedicijnInKast)

    @Query("SELECT * from medicijnen_in_kast_table WHERE medId =:key")
    fun get(key: Int): MedicijnInKast?

    @Query("SELECT * from medicijnen_in_kast_table ORDER BY naam DESC")
    fun getAllMedicijnen(): LiveData<List<MedicijnInKast>>
}