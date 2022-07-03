package com.khurram.zapkadtest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khurram.zapkadtest.R
import com.khurram.zapkadtest.presentation.view.adapter.UserAdapter
import com.khurram.zapkadtest.data.db.model.UserEntity
import com.khurram.zapkadtest.databinding.UserListFragmentBinding
import com.khurram.zapkadtest.data.network.Resource
import com.khurram.zapkadtest.util.*
import com.khurram.zapkadtest.presentation.viewmodel.NetworkStatusViewModel
import com.khurram.zapkadtest.presentation.viewmodel.NoteViewModel
import com.khurram.zapkadtest.presentation.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val userListViewModel: UserListViewModel by viewModels()
    private val noteViewModel: NoteViewModel by viewModels()
    private val networkStatusViewModel: NetworkStatusViewModel by viewModels()
    private lateinit var binding: UserListFragmentBinding
    private var usersList = listOf<UserEntity>()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()

        observer()
    }

    private fun observer() {
        getUsersList()

        searchUserFromList()

        networkStatus()
    }

    private fun networkStatus() {
        networkStatusViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                MyState.Fetched -> {
                    getUsersList()
                }
                MyState.Error -> {
                    binding.parent.showSnackBar(getString(R.string.internet_connection_disconnected))
                }
            }
        }
    }

    private fun searchUserFromList() {
        binding.etSearch.doAfterTextChanged {
            if (it.toString().isNullOrEmpty()) {
                userAdapter.submitList(usersList)
            } else {
                val name = it.toString()
                val list = usersList.filter { userEntity -> userEntity.login.contains(name) }
                userAdapter.submitList(list)
            }
        }
    }

    private fun getUsersList() {
        userListViewModel.users.observe(viewLifecycleOwner, { resource ->
            updateUI(userAdapter, resource)
            when (resource) {
                is Resource.Loading -> {
                    binding.loader.progressBar.visible()
                }
                is Resource.Success -> {
                    binding.loader.progressBar.gone()
                    updateUI(userAdapter, resource)
                }
                is Resource.Error -> {
                    if (resource.data.isNullOrEmpty()) {
                        binding.parent.showSnackBar(Helper.showError(resource.error))
                    }
                    binding.loader.progressBar.gone()
                }
            }
        })
    }

    private fun adapter() {
        userAdapter = UserAdapter(note = { username ->
            runBlocking { noteViewModel.isNoteAvailable(username = username) }
        }, onItemClicked = { username ->
            val directions =
                UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(username = username)
            findNavController().navigate(directions)
        })

        binding.apply {
            rvUser.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun updateUI(
        userAdapter: UserAdapter,
        resource: Resource<List<UserEntity>>
    ) {
        usersList = resource.data!!
        userAdapter.submitList(usersList)
    }

    private fun initialize(){
        adapter()
    }
}