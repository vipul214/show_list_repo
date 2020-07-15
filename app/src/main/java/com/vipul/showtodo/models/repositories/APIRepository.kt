package com.vipul.showtodo.models.repositories


import com.vipul.showtodo.models.data.ToDoListData
import retrofit2.Call

class APIRepository(apiRemoteDataSource: APIRemoteDataSource) {

    val apiDao by lazy {
        apiRemoteDataSource.getAPIDaoObject()
    }

    fun getToDoFromDao(): Call<ArrayList<ToDoListData>> {
        return apiDao.getToDoListFromServer()
    }
}