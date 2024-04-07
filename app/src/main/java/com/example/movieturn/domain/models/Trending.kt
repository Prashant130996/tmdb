package com.example.movieturn.domain.models

import com.example.movieturn.utils.MediaType

data class Trending(
    val id: Int,
    val mediaType: MediaType,
    val name: String,
    val rating: Double,
    val posterPath: String? = null
)