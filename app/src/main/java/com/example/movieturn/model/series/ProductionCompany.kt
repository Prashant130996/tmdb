package com.example.movieturn.model.series

data class ProductionCompany(
    val id: Int,
    val logo_path: String? = null,
    val name: String,
    val origin_country: String
)