package com.khurram.zapkadtest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khurram.zapkadtest.data.db.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users : List<UserEntity>)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

}