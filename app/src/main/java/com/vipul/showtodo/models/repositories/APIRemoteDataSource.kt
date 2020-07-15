package com.vipul.showtodo.models.repositories

import retrofit2.Retrofit

class APIRemoteDataSource(retrofit: Retrofit) {

    private val retrofit by lazy {
        retrofit
    }

    fun getAPIDaoObject(): APIDao{
        return retrofit.create(APIDao::class.java)
    }
}