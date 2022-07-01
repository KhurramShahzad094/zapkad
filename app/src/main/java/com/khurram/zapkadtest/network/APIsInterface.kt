package com.khurram.zapkadtest.network

import com.khurram.zapkadtest.data.model.UserDetailEntity
import com.khurram.zapkadtest.data.model.UserEntity
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIsInterface {

    @Headers("Accept: application/json")
    @GET("users?since=0")
    suspend fun getUsersList(): List<UserEntity>

    @Headers("Accept: application/json")
    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username : String): UserDetailEntity
}