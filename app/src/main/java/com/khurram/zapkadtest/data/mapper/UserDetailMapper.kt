package com.khurram.zapkadtest.data.mapper

import com.khurram.zapkadtest.data.db.model.UserDetailEntity
import com.khurram.zapkadtest.data.network.model.UserDetailRemote

fun UserDetailRemote.toUserDetailEntity() = UserDetailEntity(
    login = login!!,
    avatar_url = avatarUrl,
    name = name,
    company = company,
    blog = blog,
    followers = followers,
    following = following,
)

