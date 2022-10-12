package com.example.travelapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var instance: BaseApp? = null

        fun applicationContext() = instance!!.applicationContext
    }
}