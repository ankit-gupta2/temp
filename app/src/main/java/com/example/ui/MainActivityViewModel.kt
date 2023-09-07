package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.network.AuthAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authAPI: AuthAPI
) : ViewModel() {
    suspend fun makeRequest() : String {
        var resultString = ""
        withContext(Dispatchers.IO) {
            try {
                val result = authAPI.authenticateUser(email = "a@a.c", password = "aaaaaaaa")
                resultString = if (result.isSuccessful) {
                    result.body()?.ifBlank { "Empty response" } ?: "Null response"
                }else {
                    "Failed request : ${result.errorBody()?.string()}"
                }
            }catch (e: Exception) {
                resultString = e.message ?: "Exception"
            }
        }
        return resultString
    }

}