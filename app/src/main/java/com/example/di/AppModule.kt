package com.example.di

import com.example.data.network.AuthAPI
import com.example.data.network.RetrofitApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return RetrofitApiService.getInstance()
    }

    @Singleton
    @Provides
    fun getAuthApiService(retrofit: Retrofit) : AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }
}