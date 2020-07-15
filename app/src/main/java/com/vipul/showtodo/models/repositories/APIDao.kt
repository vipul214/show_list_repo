package com.vipul.showtodo.models.repositories

import com.vipul.showtodo.models.data.ToDoListData
import retrofit2.Call
import retrofit2.http.GET

interface APIDao {

    @GET("todos")
    fun getToDoListFromServer() :Call<ArrayList<ToDoListData>>
}