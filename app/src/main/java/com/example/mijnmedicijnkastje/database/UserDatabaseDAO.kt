package com.example.mijnmedicijnkastje.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDatabaseDAO {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from user_table WHERE voornaam =:key")
    suspend fun getUserByName(key: String): User?

    @Query("SELECT * from user_table ORDER BY naam DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDagMed(dagelijkseMedicatie: DagelijkseMedicatie)

    @Delete
    suspend fun deleteDagMed(dagelijkseMedicatie: DagelijkseMedicatie)

    @Transaction
    @Query("SELECT * FROM dag_med_table where idUser = :key")
    suspend fun getDagMedAndUser(key: Int): List<DagelijkseMedicatie>

    @Transaction
    @Query("SELECT * FROM dag_med_table")
    fun getAllDagMedAndUser(): LiveData<List<DagelijkseMedicatie>>

}