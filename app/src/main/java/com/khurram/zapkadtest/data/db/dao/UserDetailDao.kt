package com.khurram.zapkadtest.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khurram.zapkadtest.data.model.UserDetailEntity
import com.khurram.zapkadtest.data.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(users : UserDetailEntity)

    @Query("SELECT * FROM user_detail WHERE login=:username")
    fun getUserDetail(username : String): Flow<UserDetailEntity>

    @Query("DELETE FROM user_detail")
    suspend fun deleteUserDetail()

}