package com.example.movieturn.ui.profile

import com.example.movieturn.model.account.AccDetailsRes
import com.example.movieturn.network.ApiClient
import com.example.movieturn.res.SimpleResponse
import javax.inject.Inject

class ProfileRepo @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getAccountDetails(): AccDetailsRes? {
        val request = apiClient.getAccDetails()
        if (request.failed or !request.isSuccessful) return null
        return request.body
    }
}