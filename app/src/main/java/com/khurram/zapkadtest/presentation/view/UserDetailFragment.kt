package com.khurram.zapkadtest.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.khurram.zapkadtest.R
import com.khurram.zapkadtest.data.model.NoteEntity
import com.khurram.zapkadtest.data.model.UserDetailEntity
import com.khurram.zapkadtest.databinding.UserDetailFragmentBinding
import com.khurram.zapkadtest.data.network.Resource
import com.khurram.zapkadtest.util.*
import com.khurram.zapkadtest.presentation.viewmodel.NetworkStatusViewModel
import com.khurram.zapkadtest.presentation.viewmodel.NoteViewModel
import com.khurram.zapkadtest.presentation.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val userDetailViewModel : UserDetailViewModel by viewModels()
    private val noteViewModel : NoteViewModel by viewModels()
    private val networkStatusViewModel: NetworkStatusViewModel by viewModels()
    private lateinit var binding: UserDetailFragmentBinding
    val args: UserDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()

        observer()
    }

    private fun getNote() {
        noteViewModel.getNote(username = args.username!!).observe(viewLifecycleOwner, { noteModel ->
            noteModel?.let {
                binding.etNote.setText(noteModel.note)
            }
        })
    }

    private fun getUserDetail() {
        userDetailViewModel.getUserDetail(username = args.username!!)
            .observe(viewLifecycleOwner, { resource ->
                updateUI(data = resource.data)
                when (resource) {
                    is Resource.Loading -> {
                        binding.loader.progressBar.visible()
                    }
                    is Resource.Success -> {
                        binding.loader.progressBar.gone()
                        updateUI(data = resource.data)
                    }
                    is Resource.Error -> {
                        if (resource.data == null) {
                            binding.parent.showSnackBar(Helper.showError(resource.error))
                        }
                        binding.loader.progressBar.gone()

                    }
                }
            })
    }

    private fun clickListener() {
        binding.apply {
            toolbar.title = args.username

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            btnSaveNote.setOnClickListener {
                val note = etNote.text.toString()
                val noteEntity = NoteEntity(login = args.username!!, note = note)
                noteViewModel.insertNote(noteEntity = noteEntity)
            }
        }
    }

    private fun observer() {
        getUserDetail()

        getNote()

        networkStatus()
    }

    private fun networkStatus() {
        networkStatusViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                MyState.Fetched -> {
                    getUserDetail()
                }
                MyState.Error -> {
                    binding.parent.showSnackBar(getString(R.string.internet_connection_disconnected))
                }
            }
        }
    }

    private fun initialize() {
        clickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(data: UserDetailEntity?) {
        binding.apply {
            data?.let {
                tvUsername.text = "Name: ${data.name.ifNotNullThenReturn()}"
                tvBlog.text = "Blog: ${data.blog.ifNotNullThenReturn()}"
                tvCompany.text = "Company: ${data.company.ifNotNullThenReturn()}"
                tvFollowers.text = "Followers: ${data.followers.ifNotNullThenReturn()}"
                tvFollowing.text ="Following: ${data.following.ifNotNullThenReturn()}"

                Glide.with(context!!)
                    .load(data.avatar_url)
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .into(ivUser)
            }

        }
    }
}