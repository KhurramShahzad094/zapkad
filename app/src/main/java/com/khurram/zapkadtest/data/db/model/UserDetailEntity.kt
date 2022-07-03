package com.khurram.zapkadtest.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail")
data class UserDetailEntity(
    @PrimaryKey val login : String,
    val avatar_url : String?,
    val name : String?,
    val company : String?,
    val blog : String?,
    val followers : Int?,
    val following : Int?,
)