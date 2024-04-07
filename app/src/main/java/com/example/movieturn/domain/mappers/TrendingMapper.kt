package com.example.movieturn.domain.mappers

import com.example.movieturn.domain.models.Trending
import com.example.movieturn.model.trending.Result
import com.example.movieturn.utils.Constants
import com.example.movieturn.utils.MediaType

object TrendingMapper {

    fun buildFrom(result: Result): Trending {
        return Trending(
            id = result.id,
            mediaType = when (result.media_type) {
                "movie" -> MediaType.MOVIE
                "tv" -> MediaType.SERIES
                "person" -> MediaType.PEOPLE
                else -> MediaType.MOVIE
            },
            rating = result.vote_average ?: 0.0,
            name = result.name ?: result.title!!,
            posterPath = Constants.getPosterPath(result.poster_path),
        )
    }
}