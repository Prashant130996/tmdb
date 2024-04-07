package com.example.movieturn.network

import com.example.movieturn.domain.models.Series

object SeriesCache {
    val seriesMap = mutableMapOf<Int, Series>()
}