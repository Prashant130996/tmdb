package com.example.movieturn.network

import com.example.movieturn.model.favState.AddDelFavBody
import com.example.movieturn.model.account.AccDetailsRes
import com.example.movieturn.model.favState.AddDelFavRes
import com.example.movieturn.model.favState.FavStateRes
import com.example.movieturn.model.movie.MovieByIdRes
import com.example.movieturn.model.series.SeriesByIdRes
import com.example.movieturn.model.trending.TrendingRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("account/{account_id}")
    suspend fun getAccountDetail(@Path("account_id") accId: Int): Response<AccDetailsRes>

    @GET("trending/all/day")
    suspend fun getAllTrending(@Query("page") pageIndex: Int): Response<TrendingRes>

    @GET("search/multi")
    suspend fun searchQuery(
        @Query("page") pageIndex: Int,
        @Query("query") q: String
    ): Response<TrendingRes>

    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(@Path("series_id") id: Int): Response<SeriesByIdRes>

    @GET("tv/{series_id}/account_states")
    suspend fun getFavSeriesState(@Path("series_id") id: Int): Response<FavStateRes>

    @GET("movie/{movie_id}/account_states")
    suspend fun getFavMovieState(@Path("movie_id") id: Int): Response<FavStateRes>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): Response<MovieByIdRes>

    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavTv(@Path("account_id") accId: Int): Response<TrendingRes>

    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavMovie(@Path("account_id") accId: Int): Response<TrendingRes>

    @POST("account/{account_id}/favorite")
    suspend fun addRemoveFav(
        @Path("account_id") accId: Int,
        @Body addDelFavBody: AddDelFavBody
    ): Response<AddDelFavRes>

}