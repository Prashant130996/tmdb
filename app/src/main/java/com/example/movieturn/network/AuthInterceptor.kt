package com.example.movieturn.network

import com.example.movieturn.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(
            "Authorization",
            "Bearer ${BuildConfig.TMDB_API_KEY}" // added to local.properties
        )
        return chain.proceed(request.build())
    }

}