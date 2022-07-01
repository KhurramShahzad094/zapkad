package com.khurram.zapkadtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey var login : String,
    var avatar_url : String,
)