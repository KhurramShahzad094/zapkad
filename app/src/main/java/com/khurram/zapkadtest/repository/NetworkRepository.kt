package com.khurram.zapkadtest.repository

import androidx.room.withTransaction
import com.khurram.zapkadtest.data.db.UserDatabase
import com.khurram.zapkadtest.network.APIsInterface
import com.khurram.zapkadtest.network.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private var apisInterface: APIsInterface,
    private var database: UserDatabase
) {

    private val userDao = database.userDao()
    private val userDetailDao = database.userDetailDao()

    fun getUsersList() = networkBoundResource(
        query = {
                userDao.getAllUsers()
        },
        fetch = {
                delay(2000)
            apisInterface.getUsersList()
        },
        saveFetchResult = {users ->
            database.withTransaction {
                userDao.deleteAllUsers()
                userDao.insertUsers(users)
            }

        },

    )

    fun getUserDetail(username: String) = networkBoundResource(
        query = {
                userDetailDao.getUserDetail(username = username)
        },
        fetch = {
                delay(1000)
            apisInterface.getUserDetail(username = username)
        },
        saveFetchResult = { userDetail ->
            userDetailDao.insertUserDetail(userDetail)

        }
    )
 }