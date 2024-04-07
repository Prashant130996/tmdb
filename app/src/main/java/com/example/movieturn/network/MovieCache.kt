package com.example.movieturn.network

import com.example.movieturn.domain.models.Movie

object MovieCache {
    val movieMap = mutableMapOf<Int, Movie>()
}