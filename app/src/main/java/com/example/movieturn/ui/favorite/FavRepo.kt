package com.example.movieturn.ui.favorite

import com.example.movieturn.model.trending.TrendingRes
import com.example.movieturn.network.ApiClient
import javax.inject.Inject

class FavRepo @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getFavMovie(): TrendingRes? {
        val request = apiClient.getFavMovie()
        if (request.failed or !request.isSuccessful) return null
        return request.body
    }

    suspend fun getFavSeries(): TrendingRes? {
        val request = apiClient.getFavTv()
        if (request.failed or !request.isSuccessful) return null
        return request.body
    }
}