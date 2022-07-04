package com.khurram.zapkadtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.JsonElement
import com.khurram.zapkadtest.data.db.model.UserEntity
import com.khurram.zapkadtest.data.mapper.toUserEntity
import com.khurram.zapkadtest.data.network.Resource
import com.khurram.zapkadtest.data.network.model.UserRemote
import com.khurram.zapkadtest.data.repository.NetworkRepository
import com.khurram.zapkadtest.presentation.viewmodel.UserListViewModel
import com.khurram.zapkadtest.utils.MainCoroutineScopeRule
import com.khurram.zapkadtest.utils.getValueForTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

class UserListViewModelShould {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    lateinit var repository : NetworkRepository
    lateinit var viewModel: UserListViewModel
    val usersList = mock<List<UserEntity>>()


    private val expected : Resource<List<UserEntity>> = Resource.Success(usersList)
    private val exception = RuntimeException("Something went wrong")

    @Before
    fun setup(){
        repository = mock()
        viewModel = UserListViewModel(repository)
    }

    @Test
    fun callTheGetUserLists() = runBlockingTest {

        whenever(repository.getUsersList()).thenReturn(flow {
            emit(expected)
        })

       viewModel.users.getValueForTest()
        verify(repository, times(1)).getUsersList()
    }

    @Test
    fun emitUsersListFromRepository() = runBlockingTest {

        whenever(repository.getUsersList()).thenReturn(flow {
            emit(expected)
        })

        assertEquals(expected, viewModel.users.getValueForTest())
    }
}