package com.vipul.showtodo.models.utils

import com.vipul.showtodo.models.constants.showtodoConstant
import com.vipul.showtodo.models.repositories.APIRemoteDataSource
import com.vipul.showtodo.models.repositories.APIRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppContainer {

    private val okHTTPClient = OkHttpClient.Builder()
        .connectTimeout(showtodoConstant.CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(showtodoConstant.READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(showtodoConstant.WRITE_TIME_OUT, TimeUnit.SECONDS)

    private val retrofit = Retrofit.Builder()
    .baseUrl(showtodoConstant.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHTTPClient.build())
    .build()

    private val apiRemoteDataSource = APIRemoteDataSource(retrofit)

    val apiRepository = APIRepository(apiRemoteDataSource)
}