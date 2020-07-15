package com.vipul.showtodo.models.utils

import android.app.Application
import android.content.Context

class ShowToDoApplication : Application() {

    init {
        appInstance = this
    }

    companion object {
        private var appInstance: ShowToDoApplication? = null
        fun getAppContext(): Context {
            return appInstance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    val appContainer by lazy {
        AppContainer()
    }
}