package com.example.movieturn.model.favState

data class AddDelFavBody(
    val media_type: String,
    val media_id: Int,
    val favorite: Boolean
)
