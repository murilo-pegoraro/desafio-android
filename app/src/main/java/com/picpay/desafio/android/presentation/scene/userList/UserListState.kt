package com.picpay.desafio.android.presentation.scene.userList

import com.picpay.desafio.android.domain.model.User

sealed class UserListStates {
    data class SuccessState(val data: List<User>) : UserListStates()
    data class LoadingState(val visible: Boolean) : UserListStates()
    object EmptyState : UserListStates()
    object ErrorState : UserListStates()
}