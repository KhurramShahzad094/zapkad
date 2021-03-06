package com.khurram.zapkadtest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khurram.zapkadtest.data.db.model.UserDetailEntity
import com.khurram.zapkadtest.data.network.Resource
import com.khurram.zapkadtest.data.repository.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {

    fun getUserDetail(username : String): LiveData<Resource<UserDetailEntity>> {
        return repository.getUserDetail(username = username).asLiveData()
    }
}