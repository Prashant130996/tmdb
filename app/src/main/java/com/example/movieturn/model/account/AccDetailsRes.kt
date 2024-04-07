package com.example.movieturn.model.account

data class AccDetailsRes(
    val avatar: Avatar? = null,
    val id: Int,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String,
    val name: String,
    val username: String
)