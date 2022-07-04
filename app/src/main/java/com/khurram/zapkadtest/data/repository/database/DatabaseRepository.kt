package com.khurram.zapkadtest.data.repository.database

import com.khurram.zapkadtest.data.db.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun insertNote(noteEntity: NoteEntity) : Unit

    fun getNote(username: String) : Flow<NoteEntity?>

    suspend fun isNoteAvailable(username: String) : Boolean
 }