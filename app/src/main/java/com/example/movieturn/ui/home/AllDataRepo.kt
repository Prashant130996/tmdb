package com.example.movieturn.ui.home

import com.example.movieturn.model.trending.TrendingRes
import com.example.movieturn.network.ApiClient
import com.example.movieturn.res.SimpleResponse
import javax.inject.Inject

class AllDataRepo @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getTrending(pageIndex: Int): SimpleResponse<TrendingRes>? {
        val request = apiClient.getTrendingData(pageIndex)
        if (request.failed or !request.isSuccessful) return null
        return request
    }

    suspend fun searchQuery(query: String, pageIndex: Int): SimpleResponse<TrendingRes>? {
        val request = apiClient.searchQuery(query, pageIndex)
        if (request.failed or !request.isSuccessful) return null
        return request
    }
}