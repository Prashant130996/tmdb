package com.example.movieturn.domain.mappers

import com.example.movieturn.domain.models.Movie
import com.example.movieturn.model.movie.MovieByIdRes
import com.example.movieturn.utils.Constants

object MovieMapper {
    fun buildFrom(movieByIdRes: MovieByIdRes, favMovie: Boolean): Movie {
        return Movie(
            backdropPath = Constants.getBackdropPath(movieByIdRes.backdrop_path),
            genres = movieByIdRes.genres!!.map { it.name },
            id = movieByIdRes.id,
            isFav = favMovie,
            overview = movieByIdRes.overview ?: "",
            popularity = movieByIdRes.popularity ?: 0.0,
            posterPath = movieByIdRes.poster_path ?: "",
            releaseDate = movieByIdRes.release_date ?: "",
            runtime = movieByIdRes.runtime ?: 0,
            tagline = movieByIdRes.tagline ?: "",
            title = movieByIdRes.title ?: ""
        )
    }

}