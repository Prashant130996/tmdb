package com.example.movieturn.domain.mappers

import com.example.movieturn.domain.models.Trending
import com.example.movieturn.model.trending.Result
import com.example.movieturn.utils.Constants
import com.example.movieturn.utils.MediaType

object FavMapper {

    fun buildFrom(result: Result, series: MediaType): Trending {
        return Trending(
            id = result.id,
            mediaType = when (series) {
                MediaType.MOVIE -> MediaType.MOVIE
                MediaType.SERIES -> MediaType.SERIES
                MediaType.PEOPLE -> MediaType.PEOPLE
                else -> MediaType.MOVIE
            },
            rating = result.vote_average ?: 0.0,
            name = result.name ?: result.title!!,
            posterPath = Constants.getPosterPath(result.poster_path),
        )
    }
}