package com.picpay.desafio.android.presentation.scene.userList

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.extension.visible
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.user = user
        binding.progressBar.visible(true)
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visible(false)
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visible(false)
                }
            })
    }
}