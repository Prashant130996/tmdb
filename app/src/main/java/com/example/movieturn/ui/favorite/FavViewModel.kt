package com.example.movieturn.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieturn.domain.mappers.FavMapper
import com.example.movieturn.domain.models.Trending
import com.example.movieturn.model.trending.TrendingRes
import com.example.movieturn.utils.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(private val favRepo: FavRepo) : ViewModel() {

    private var _getFavTvLiveData = MutableLiveData<TrendingRes?>()
    private var _getFavMovieLiveData = MutableLiveData<TrendingRes?>()
    val getFavTvLiveData: LiveData<TrendingRes?> = _getFavTvLiveData
    val getFavMovieLiveData: LiveData<TrendingRes?> = _getFavMovieLiveData
    var isTvFetched = false
    var isMovieFetched = false

    fun getFavMovie() {
        viewModelScope.launch {
            val res = favRepo.getFavMovie()
            _getFavMovieLiveData.postValue(res)
        }
    }

    fun getFavSeries() {
        viewModelScope.launch {
            val res = favRepo.getFavSeries()
            _getFavTvLiveData.postValue(res)
        }
    }

    private var _getList = MutableLiveData<List<Trending>?>()
    val getList: LiveData<List<Trending>?> = _getList
    fun addData() {
        if (isMovieFetched and isTvFetched) {
            val res1 = getFavTvLiveData.value?.results?.map { FavMapper.buildFrom(it,MediaType.SERIES) }
            val res2 = getFavMovieLiveData.value?.results?.map { FavMapper.buildFrom(it,MediaType.MOVIE) }
            val result = res1!! + res2!!
            _getList.postValue(result)
        }
    }
}