package com.khurram.zapkadtest.data.repository.database

import com.khurram.zapkadtest.data.db.UserDatabase
import com.khurram.zapkadtest.data.db.dao.NoteDao
import com.khurram.zapkadtest.data.db.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private var database: UserDatabase
) : DatabaseRepository {

    private val noteDao : NoteDao = database.noteDao()

    override suspend fun insertNote(noteEntity: NoteEntity) : Unit = noteDao.insertNote(note = noteEntity)

    override fun getNote(username: String) : Flow<NoteEntity?> = noteDao.getNote(loginId = username)

    override suspend fun isNoteAvailable(username: String) : Boolean = noteDao.isNoteAvailable(loginId = username)

}