package com.khurram.zapkadtest.data.mapper

import com.khurram.zapkadtest.data.model.UserEntity
import com.khurram.zapkadtest.data.network.model.UserRemote

fun UserRemote.toUserEntity() = UserEntity(
    login = login!!,
    avatar_url = avatarUrl!!
)