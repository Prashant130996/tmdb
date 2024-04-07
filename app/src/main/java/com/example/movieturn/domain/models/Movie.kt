package com.example.movieturn.domain.models

import com.example.movieturn.utils.MediaType


data class Movie(
    val backdropPath: String,
    val genres: List<String> = listOf(),
    val id: Int,
    val isFav: Boolean,
    val mediaType: MediaType = MediaType.MOVIE,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val tagline: String,
    val title: String
)