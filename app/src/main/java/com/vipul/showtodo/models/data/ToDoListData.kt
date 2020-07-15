package com.vipul.showtodo.models.data

import com.google.gson.annotations.SerializedName

data class ToDoListData(var userId: String, var id:Int, val title: String,
                        @SerializedName("completed")var status: Boolean )