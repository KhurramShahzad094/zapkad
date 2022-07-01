package com.khurram.zapkadtest.data.repository

import com.khurram.zapkadtest.data.db.UserDatabase
import com.khurram.zapkadtest.data.model.NoteEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private var database: UserDatabase
) {

    private val noteDao = database.noteDao()

    suspend fun insertNote(noteEntity: NoteEntity) = noteDao.insertNote(note = noteEntity)

    fun getNote(username: String) = noteDao.getNote(loginId = username)

    suspend fun isNoteAvailable(username: String) = noteDao.isNoteAvailable(loginId = username)
 }