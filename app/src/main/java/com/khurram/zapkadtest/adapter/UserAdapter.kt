package com.khurram.zapkadtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khurram.zapkadtest.R
import com.khurram.zapkadtest.data.model.UserEntity
import com.khurram.zapkadtest.databinding.ItemUserListBinding
import com.khurram.zapkadtest.util.invisible
import com.khurram.zapkadtest.util.visible

class UserAdapter(private var note:((login : String) -> Boolean),
                  private var onItemClicked: ((login : String) -> Unit)
                  ) : ListAdapter<UserEntity,UserAdapter.ViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    inner class ViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userEntity: UserEntity) {
            binding.apply {
                Glide.with(itemView)
                    .load(userEntity.avatar_url)
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .into(ivUser)

                tvUsername.text = userEntity.login
                cvParent.setOnClickListener {
                    onItemClicked(userEntity.login)
                }

                if (note(userEntity.login)){
                    ivNote.visible()
                }else{
                    ivNote.invisible()
                }
            }
        }
    }

   companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(
                oldItem: UserEntity,
                newItem: UserEntity
            ) =
                oldItem.login == newItem.login

            override fun areContentsTheSame(
                oldItem: UserEntity,
                newItem: UserEntity
            ) =
                oldItem == newItem
        }
    }
}