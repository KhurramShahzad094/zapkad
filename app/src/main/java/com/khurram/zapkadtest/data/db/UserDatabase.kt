package com.khurram.zapkadtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khurram.zapkadtest.data.db.dao.NoteDao
import com.khurram.zapkadtest.data.db.dao.UserDao
import com.khurram.zapkadtest.data.db.dao.UserDetailDao
import com.khurram.zapkadtest.data.model.NoteEntity
import com.khurram.zapkadtest.data.model.UserDetailEntity
import com.khurram.zapkadtest.data.model.UserEntity

@Database(entities = [UserEntity::class,UserDetailEntity::class,NoteEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailDao(): UserDetailDao
    abstract fun noteDao(): NoteDao
}