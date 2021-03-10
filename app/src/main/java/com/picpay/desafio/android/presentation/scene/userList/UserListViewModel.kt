package com.picpay.desafio.android.presentation.scene.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.domain.usecase.GetUserListUseCase
import com.picpay.desafio.android.presentation.base.BaseViewModel
import com.picpay.desafio.android.presentation.extension.addToDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserListViewModel(
    private val userListUseCase: GetUserListUseCase
) : BaseViewModel() {

    private val _viewUserListStatesLiveData = MutableLiveData<UserListStates>()
    val viewUserListStatesLiveData: LiveData<UserListStates> = _viewUserListStatesLiveData

    init {
        getUsers()
    }

    private fun getUsers() {
        userListUseCase.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _viewUserListStatesLiveData.value = UserListStates.LoadingState(true)
            }
            .subscribe(
                {
                    _viewUserListStatesLiveData.value = if (it.isNotEmpty()) {
                        UserListStates.SuccessState(it)
                    } else {
                        UserListStates.EmptyState
                    }
                },
                {
                    _viewUserListStatesLiveData.value = UserListStates.ErrorState
                })
            .addToDispose(disposables)
    }
}