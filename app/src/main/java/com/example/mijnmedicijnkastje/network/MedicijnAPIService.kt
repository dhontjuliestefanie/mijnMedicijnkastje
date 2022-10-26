package com.example.mijnmedicijnkastje.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://data.openstate.eu/nl/api/3/"
//private const val BASE_URL = "https://data.openstate.eu/nl/api/3/action/datastore_search/"
//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
//private const val BASE_URL = "https://data.openstate.eu/nl/api/3/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11"
private const val BASE_URL = "https://data.openstate.eu/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MedicijnAPIService {
    @GET("nl/api/3/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11")
    fun getProperties(): Call<String>
}

object MedicijnAPI {
    val retrofitService: MedicijnAPIService by lazy { retrofit.create(MedicijnAPIService::class.java) }
}