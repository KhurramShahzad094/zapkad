package com.khurram.zapkadtest.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey val login : String,
    val note : String,
)
