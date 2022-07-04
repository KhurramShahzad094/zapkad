package com.khurram.zapkadtest.data.repository.network

import com.khurram.zapkadtest.data.db.model.UserDetailEntity
import com.khurram.zapkadtest.data.db.model.UserEntity
import com.khurram.zapkadtest.data.network.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getUsersList() : Flow<Resource<List<UserEntity>>>

    fun getUserDetail(username: String) : Flow<Resource<UserDetailEntity>>
 }