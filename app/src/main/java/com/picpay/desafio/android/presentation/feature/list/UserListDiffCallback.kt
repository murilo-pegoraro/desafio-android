package com.picpay.desafio.android.presentation.feature.list

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.data.model.UserApi

class UserListDiffCallback(
    private val oldList: List<UserApi>,
    private val newList: List<UserApi>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}