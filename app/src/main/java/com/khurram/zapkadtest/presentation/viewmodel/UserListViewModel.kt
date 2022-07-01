package com.khurram.zapkadtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khurram.zapkadtest.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(repository: NetworkRepository) : ViewModel() {

   val users = repository.getUsersList().asLiveData()
}