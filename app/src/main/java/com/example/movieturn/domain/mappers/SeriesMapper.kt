package com.example.movieturn.domain.mappers

import com.example.movieturn.domain.models.Season
import com.example.movieturn.domain.models.Series
import com.example.movieturn.model.series.SeriesByIdRes
import com.example.movieturn.utils.Constants

object SeriesMapper {
    fun buildFrom(seriesByIdRes: SeriesByIdRes, favSeries: Boolean): Series {
        return Series(
            backdropPath = Constants.getBackdropPath(seriesByIdRes.backdrop_path),
            genres = seriesByIdRes.genres.map { it.name },
            id = seriesByIdRes.id,
            isFav = favSeries,
            overview = seriesByIdRes.overview ?: "",
            popularity = seriesByIdRes.popularity ?: 0.0,
            posterPath = seriesByIdRes.poster_path ?: "",
            seasons = seriesByIdRes.seasons.map {
                Season(
                    it.air_date ?: "",
                    it.episode_count ?: 0,
                    it.id,
                    it.name ?: ""
                )
            },
            firstAirDate = seriesByIdRes.first_air_date ?: "",
            name = seriesByIdRes.name ?: ""
        )
    }

}