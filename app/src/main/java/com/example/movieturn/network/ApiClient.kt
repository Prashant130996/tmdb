package com.example.movieturn.network

import com.example.movieturn.BuildConfig
import com.example.movieturn.model.account.AccDetailsRes
import com.example.movieturn.model.favState.AddDelFavBody
import com.example.movieturn.model.favState.AddDelFavRes
import com.example.movieturn.model.favState.FavStateRes
import com.example.movieturn.model.movie.MovieByIdRes
import com.example.movieturn.model.series.SeriesByIdRes
import com.example.movieturn.model.trending.TrendingRes
import com.example.movieturn.res.SimpleResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ApiClient @Inject constructor(private val tmdbService: TmdbService) {

    suspend fun getAccDetails(): SimpleResponse<AccDetailsRes> {
        return safeApiCall { tmdbService.getAccountDetail(BuildConfig.ACC_ID.toInt()) }
    }

    suspend fun getTrendingData(pageIndex: Int): SimpleResponse<TrendingRes> {
        return safeApiCall { tmdbService.getAllTrending(pageIndex) }
    }

    suspend fun getFavTv(): SimpleResponse<TrendingRes> {
        return safeApiCall { tmdbService.getFavTv(BuildConfig.ACC_ID.toInt()) }
    }

    suspend fun getFavMovie(): SimpleResponse<TrendingRes> {
        return safeApiCall { tmdbService.getFavMovie(BuildConfig.ACC_ID.toInt()) }
    }

    suspend fun searchQuery(query: String, pageIndex: Int): SimpleResponse<TrendingRes> {
        return safeApiCall { tmdbService.searchQuery(q = query, pageIndex = pageIndex) }
    }

    suspend fun getMovieByIdRes(movieId: Int): SimpleResponse<MovieByIdRes> {
        return safeApiCall { tmdbService.getMovieDetails(id = movieId) }
    }

    suspend fun getSeriesByIdRes(seriesId: Int): SimpleResponse<SeriesByIdRes> {
        return safeApiCall { tmdbService.getSeriesDetails(id = seriesId) }
    }

    suspend fun getMovieState(movieId: Int): SimpleResponse<FavStateRes> {
        return safeApiCall { tmdbService.getFavMovieState(id = movieId) }
    }

    suspend fun getSeriesState(seriesId: Int): SimpleResponse<FavStateRes> {
        return safeApiCall { tmdbService.getFavSeriesState(id = seriesId) }
    }

    suspend fun addDelFav(addDelFavBody: AddDelFavBody): SimpleResponse<AddDelFavRes> {
        return safeApiCall {
            tmdbService.addRemoveFav(
                accId = BuildConfig.ACC_ID.toInt(),
                addDelFavBody = addDelFavBody
            )
        }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}