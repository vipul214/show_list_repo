package com.vipul.showtodo.models.constants

import com.vipul.showtodo.R
import com.vipul.showtodo.models.utils.ShowToDoApplication

class showtodoConstant {
    companion object {
        val NoNetworkMessage = "No Network Connectivity"

        val CONNECT_TIME_OUT = 8L
        val READ_TIME_OUT = 5L
        val WRITE_TIME_OUT = 5L

        val BASE_URL by lazy {
            ShowToDoApplication.getAppContext().resources.getString(R.string.base_url)
        }
    }
}