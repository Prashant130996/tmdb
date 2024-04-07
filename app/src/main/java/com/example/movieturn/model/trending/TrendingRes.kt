package com.example.movieturn.model.trending

data class TrendingRes(
    val page: Int,
    val results: List<Result> = listOf(),
    val total_pages: Int,
    val total_results: Int
)