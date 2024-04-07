package com.example.movieturn.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieturn.domain.mappers.TrendingMapper
import com.example.movieturn.domain.models.Trending
import javax.inject.Inject
import kotlin.math.max

private const val STARTING_KEY = 1

class AllDataPagingSource @Inject constructor(private val allDataRepo: AllDataRepo) :
    PagingSource<Int, Trending>() {
    override fun getRefreshKey(state: PagingState<Int, Trending>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val trending = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = trending.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Trending> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY
        val range = startKey.until(startKey + params.loadSize)

        val response = allDataRepo.getTrending(startKey)
        val trendingRes = response?.body
        val trendingList = trendingRes?.results

        response?.exception?.let { return LoadResult.Error(it) }

        return LoadResult.Page(
            data = trendingList?.map { TrendingMapper.buildFrom(it) }.orEmpty(),
            prevKey = when (startKey) {
                STARTING_KEY -> null
                else -> when (val prevKey = ensureValidKey(key = range.first - params.loadSize)) {
                    // We're at the start, there's nothing more to load
                    STARTING_KEY -> null
                    else -> prevKey
                }
            },
            nextKey = if (startKey == trendingRes!!.total_pages) null else startKey + 1
        )
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}
