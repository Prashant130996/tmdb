package com.example.movieturn.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieturn.domain.models.Movie
import com.example.movieturn.domain.models.Series
import com.example.movieturn.model.favState.AddDelFavBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepo: DetailsRepo) : ViewModel() {

    private val _getMovieDetailsLiveData = MutableLiveData<Movie?>()
    val getMovieDetailsLiveData: LiveData<Movie?> = _getMovieDetailsLiveData

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = detailsRepo.getMovieDetailsById(movieId)
            _getMovieDetailsLiveData.postValue(movie)
        }
    }

    private val _getSeriesDetailsLiveData = MutableLiveData<Series?>()
    val getSeriesDetailsLiveData: LiveData<Series?> = _getSeriesDetailsLiveData
    fun getSeriesDetails(seriesId: Int) {
        viewModelScope.launch {
            val series = detailsRepo.getSeriesDetailsById(seriesId)
            _getSeriesDetailsLiveData.postValue(series)
        }
    }

    fun addRemoveFavMovie(addDelFavBody: AddDelFavBody) {
        viewModelScope.launch {
            detailsRepo.addRemoveFavMovie(addDelFavBody)
        }
    }
}