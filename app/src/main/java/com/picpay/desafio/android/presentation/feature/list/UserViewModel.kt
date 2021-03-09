package com.picpay.desafio.android.presentation.feature.list

import com.picpay.desafio.android.domain.usecase.GetUserListUseCase
import com.picpay.desafio.android.presentation.base.BaseViewModel
import com.picpay.desafio.android.presentation.extension.addToDispose

class UserViewModel(
    private val userListUseCase: GetUserListUseCase
) : BaseViewModel() {

    fun getUsers() {
        userListUseCase.getUserList().subscribe().addToDispose(disposables)
    }
}