package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.presentation.scene.userList.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserListViewModel(get()) }
}