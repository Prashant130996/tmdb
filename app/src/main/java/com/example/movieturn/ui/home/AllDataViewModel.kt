package com.example.movieturn.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieturn.domain.models.Trending
import com.example.movieturn.ui.search.SearchPagingSource
import com.example.movieturn.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AllDataViewModel @Inject constructor(private val allDataRepo: AllDataRepo) : ViewModel() {

    fun searchContent(query: String): Flow<PagingData<Trending>> {
        return Pager(
            PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                prefetchDistance = Constants.PREFETCH_DISTANCE,
                enablePlaceholders = false
            ), pagingSourceFactory = { SearchPagingSource(allDataRepo, query) }
        ).flow.cachedIn(viewModelScope)
    }

    val flow = Pager(
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        ), pagingSourceFactory = { AllDataPagingSource(allDataRepo) }
    ).flow.cachedIn(viewModelScope)
}