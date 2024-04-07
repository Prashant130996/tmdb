package com.example.movieturn.utils

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    const val PAGE_SIZE = 20
    const val PREFETCH_DISTANCE = PAGE_SIZE * 2

    @JvmStatic
    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }

    @JvmStatic
    fun getBackdropPath(backdropPath: String?): String {
        return BASE_BACKDROP_PATH + backdropPath
    }
}