package com.khurram.zapkadtest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.khurram.zapkadtest.data.model.NoteEntity
import com.khurram.zapkadtest.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {

    fun insertNote(noteEntity: NoteEntity){
        viewModelScope.launch {
            repository.insertNote(noteEntity = noteEntity)
        }
    }

    fun getNote(username : String): LiveData<NoteEntity?> {
        return repository.getNote(username = username).asLiveData()
    }

     suspend fun isNoteAvailable(username : String) = withContext(Dispatchers.IO) {
           return@withContext repository.isNoteAvailable(username = username)
    }
}