package com.example.mijnmedicijnkastje.network

import MedicijnBase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://data.openstate.eu/nl/api/3/"
//private const val BASE_URL = "https://data.openstate.eu/nl/api/3/action/datastore_search/"
//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
//private const val BASE_URL = "https://data.openstate.eu/nl/api/3/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11"
//private const val BASE_URL = "https://data.openstate.eu/"
//private const val BASE_URL = "https://covid-193.p.rapidapi.com/"
//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"




//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



interface MedicijnAPIService {
    //    @Headers("x-rapidapi-host: https://data.openstate.eu/nl/api/3/action/", "resource_id:1efaa651-add9-40f5-8b0c-2c2f2d352e11")
//    @GET("datastore_search")
    @GET("https://data.openstate.eu/nl/api/3/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11")
//    @GET("realestate")
    fun getProperties(): Call<MedicijnBase>
//     fun getProperties(): MedicijnBase
}


//interface MedicijnAPIService {
////    @Headers("resource-id:1efaa651-add9-40f5-8b0c-2c2f2d352e11")
////    @GET(
////        "datastore_search")
//        @GET("https://data.openstate.eu/nl/api/3/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11")
////    fun getProperties(): Call<List<Records>>
//        suspend fun getMedicijnByName(@Query("datastore_search") q: String): MedicijnBase
//

//}

//interface MedicijnAPIService {
//    //    @GET("/action/datastore_search")
////    @GET("/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11")
//    @GET("/nl/api3/action/datastore_search?resource_id=1efaa651-add9-40f5-8b0c-2c2f2d352e11")
////        @Headers("resource-id:1efaa651-add9-40f5-8b0c-2c2f2d352e11")
////    @GET("/nl/api3/action/datastore_search")
//
////    suspend fun getAllMedicines(): MedicijnBase
//
////    suspend fun getAllMedicines(): Response<Response>
//    suspend fun getAllMedicines(): List<Result>
//}

object MedicijnAPI {
    val retrofitService: MedicijnAPIService by lazy { retrofit.create(MedicijnAPIService::class.java) }
}