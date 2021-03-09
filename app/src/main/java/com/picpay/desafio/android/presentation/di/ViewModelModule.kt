package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.domain.usecase.GetUserListUseCase
import com.picpay.desafio.android.presentation.feature.list.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}