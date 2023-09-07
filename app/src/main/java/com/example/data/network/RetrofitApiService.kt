package com.example.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthAPI {
    @GET("auth-user")
    suspend fun authenticateUser(
        @Query("email") email : String,
        @Query("password") password : String,
    ) : Response<String>
}

class RetrofitApiService {

    companion object {
        private const val BASE_URL = "http://localhost:8080/Gradle___com_example___first_jsp_1_0_SNAPSHOT_war/"
        private var instance : Retrofit? = null

        @JvmStatic
        fun getInstance(): Retrofit {
//            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            return instance ?: synchronized(this) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create().asLenient())
                    .build()

                instance!!
            }
        }
    }
}
