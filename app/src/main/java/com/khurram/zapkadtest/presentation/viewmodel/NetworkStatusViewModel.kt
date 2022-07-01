package com.khurram.zapkadtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.khurram.zapkadtest.util.MyState
import com.khurram.zapkadtest.util.NetworkStatusTracker
import com.khurram.zapkadtest.util.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NetworkStatusViewModel @Inject constructor(
    networkStatusTracker: NetworkStatusTracker,
) : ViewModel() {

    val state =
        networkStatusTracker.networkStatus
            .map(
                onAvailable = { MyState.Fetched },
                onUnavailable = { MyState.Error },
            )
            .asLiveData(Dispatchers.IO)
}