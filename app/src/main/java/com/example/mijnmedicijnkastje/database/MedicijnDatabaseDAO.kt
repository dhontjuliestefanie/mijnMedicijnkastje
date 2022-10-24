package com.example.mijnmedicijnkastje.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicijnDatabaseDAO {
    @Insert
    suspend fun insert(medicijn: MedicijnInKast)

    @Update
    suspend fun update(medicijn: MedicijnInKast)

    @Delete
    suspend fun delete(medicijn: MedicijnInKast)

    @Query("SELECT * from medicijnen_in_kast_table WHERE medId =:key")
    suspend fun get(key: Int): MedicijnInKast?

    @Query("SELECT * FROM medicijnen_in_kast_table ORDER BY medId DESC LIMIT 1")
    fun getMed(): MedicijnInKast?

    @Query("SELECT * from medicijnen_in_kast_table ORDER BY naam DESC")
    fun getAllMedicijnen(): LiveData<List<MedicijnInKast>>
}