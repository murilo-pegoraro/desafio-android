package com.picpay.desafio.android.presentation.scene.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    private var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    fun setData(data: List<User>) {
        users = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemUserBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_user,
            parent,
            false
        )
        return UserListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}