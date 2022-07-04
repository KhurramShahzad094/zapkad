package com.khurram.zapkadtest.data.repository.network

import androidx.room.withTransaction
import com.khurram.zapkadtest.data.db.UserDatabase
import com.khurram.zapkadtest.data.db.model.UserDetailEntity
import com.khurram.zapkadtest.data.db.model.UserEntity
import com.khurram.zapkadtest.data.mapper.toUserDetailEntity
import com.khurram.zapkadtest.data.mapper.toUserEntity
import com.khurram.zapkadtest.data.network.APIsInterface
import com.khurram.zapkadtest.data.network.Resource
import com.khurram.zapkadtest.data.network.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private var apisInterface: APIsInterface,
    private var database: UserDatabase
) : NetworkRepository {


    private val userDao = database.userDao()
    private val userDetailDao = database.userDetailDao()

    override fun getUsersList(): Flow<Resource<List<UserEntity>>> {
        return networkBoundResource(
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
                    userDao.insertUsers(users.map {
                        it.toUserEntity()
                    })
                }

            },

            )
    }

    override fun getUserDetail(username: String): Flow<Resource<UserDetailEntity>> {
        return networkBoundResource(
            query = {
                userDetailDao.getUserDetail(username = username)
            },
            fetch = {
                delay(1000)
                apisInterface.getUserDetail(username = username)
            },
            saveFetchResult = { userDetail ->
                userDetailDao.insertUserDetail(userDetail.toUserDetailEntity())
            }
        )
    }
}