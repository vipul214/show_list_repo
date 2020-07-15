package com.vipul.showtodo.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.vipul.showtodo.models.data.ToDoListData
import com.vipul.showtodo.models.data.ToDoWrapperData
import com.vipul.showtodo.models.repositories.APIRepository
import com.vipul.showtodo.models.utils.ShowToDoApplication
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class showtodoViewModel(apiRepository: APIRepository) : ViewModel() {

    private val toDoList = CompletableDeferred<ToDoWrapperData>()

    private val apiRepository by lazy {
        apiRepository
    }

    private var callBackForGetListAPI = object : Callback<ArrayList<ToDoListData>> {
        override fun onFailure(call: Call<ArrayList<ToDoListData>>, t: Throwable) {
            if (t is IOException) {
                toDoList.complete(ToDoWrapperData(false, null))
            } else {
                toDoList.complete(ToDoWrapperData(true, null))
            }
        }

        override fun onResponse(
            call: Call<ArrayList<ToDoListData>>,
            response: Response<ArrayList<ToDoListData>>
        ) {
            toDoList.complete(ToDoWrapperData(true, response.body()))
        }

    }

    suspend fun getToDoList(): ToDoWrapperData {
        if (isNetworkConnected()) {
            var toDoList = getToDoListFromRepository()
            return toDoList
        } else {
            return ToDoWrapperData(false, null)
        }
    }

    private suspend fun getToDoListFromRepository(): ToDoWrapperData {
        apiRepository.getToDoFromDao().enqueue(callBackForGetListAPI)

        return toDoList.await()
    }

    private fun isNetworkConnected(): Boolean {
        val cm =
            ShowToDoApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                val ni = cm.activeNetworkInfo

                if (ni != null) {
                    return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
                }
            } else {
                val n = cm.activeNetwork

                if (n != null) {
                    val nc = cm.getNetworkCapabilities(n)

                    return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                }
            }
        }

        return false
    }

}