package com.khurram.zapkadtest.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HiltApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        // Used to load the 'cmake' library on application startup.
        init {
            System.loadLibrary("cmake")
        }
    }
}
