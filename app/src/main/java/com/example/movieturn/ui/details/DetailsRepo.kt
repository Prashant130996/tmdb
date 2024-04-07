package com.example.movieturn.ui.details

import com.example.movieturn.domain.mappers.MovieMapper
import com.example.movieturn.domain.mappers.SeriesMapper
import com.example.movieturn.domain.models.Movie
import com.example.movieturn.domain.models.Series
import com.example.movieturn.model.favState.AddDelFavBody
import com.example.movieturn.model.favState.AddDelFavRes
import com.example.movieturn.model.favState.FavStateRes
import com.example.movieturn.network.ApiClient
import javax.inject.Inject

class DetailsRepo @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getMovieDetailsById(id: Int): Movie? {
        val request = apiClient.getMovieByIdRes(movieId = id)
        if (request.failed or !request.isSuccessful) return null
        val favMovie = getMovieState(id)
        return MovieMapper.buildFrom(request.body, favMovie?.favorite ?: false)
    }

    suspend fun getSeriesDetailsById(id: Int): Series? {
        val request = apiClient.getSeriesByIdRes(seriesId = id)
        if (request.failed or !request.isSuccessful) return null
        val favSeries = getSeriesState(id)
        return SeriesMapper.buildFrom(request.body, favSeries?.favorite ?: false)
    }

    suspend fun addRemoveFavMovie(addDelFavBody: AddDelFavBody): AddDelFavRes? {
        val request = apiClient.addDelFav(addDelFavBody)
        if (request.failed or !request.isSuccessful) return null
        return request.body
    }

    private suspend fun getMovieState(id: Int): FavStateRes? {
        val request = apiClient.getMovieState(movieId = id)
        if (request.failed or !request.isSuccessful) return null
        return request.body
    }

    private suspend fun getSeriesState(id: Int): FavStateRes? {
        val request = apiClient.getSeriesState(seriesId = id)
        if (request.failed or !request.isSuccessful) return null
        return request.body
    }
}