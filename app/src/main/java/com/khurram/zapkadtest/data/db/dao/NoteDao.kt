package com.khurram.zapkadtest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khurram.zapkadtest.data.db.model.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : NoteEntity)

    @Query("SELECT * FROM note WHERE login=:loginId")
    fun getNote(loginId : String): Flow<NoteEntity?>

    @Query("SELECT EXISTS(SELECT * FROM note WHERE login = :loginId AND note !='')")
    suspend fun isNoteAvailable(loginId : String) : Boolean

    @Query("DELETE FROM note")
    suspend fun deleteNote()

}