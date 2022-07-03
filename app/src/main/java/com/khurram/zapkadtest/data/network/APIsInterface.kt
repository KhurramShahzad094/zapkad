package com.khurram.zapkadtest.data.network

import com.khurram.zapkadtest.data.network.model.UserDetailRemote
import com.khurram.zapkadtest.data.network.model.UserRemote
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIsInterface {

    @Headers("Accept: application/json")
    @GET("users?since=0")
    suspend fun getUsersList(): List<UserRemote>

    @Headers("Accept: application/json")
    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username : String): UserDetailRemote
}