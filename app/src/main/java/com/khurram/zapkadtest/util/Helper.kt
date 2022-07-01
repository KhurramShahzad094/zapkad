package com.khurram.zapkadtest.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Helper {

    fun showError(it : Throwable?) : String{
        return when (it) {
            is HttpException -> {
                "Please check your backend"
            }
            is SocketTimeoutException -> {
                "Please check your internet"
            }
            is UnknownHostException -> {
                "Please connect to internet"
            }
            else -> {
                "Something went wrong, please try again later"
            }
        }
    }

    fun hideKeyboard(activity: Activity) {
        val v = activity.window.currentFocus
        if (v != null) {
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}