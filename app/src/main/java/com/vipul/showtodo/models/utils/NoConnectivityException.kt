package com.vipul.showtodo.models.utils

import com.vipul.showtodo.models.constants.showtodoConstant
import java.io.IOException
import java.lang.Exception

class NoConnectivityException : IOException() {

    override fun getLocalizedMessage(): String? {
        return showtodoConstant.NoNetworkMessage
    }
}