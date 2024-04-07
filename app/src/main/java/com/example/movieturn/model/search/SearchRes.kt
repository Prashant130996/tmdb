package com.example.movieturn.model.search

data class SearchRes(
    val page: Int,
    val results: List<Result> = listOf(),
    val total_pages: Int,
    val total_results: Int
)