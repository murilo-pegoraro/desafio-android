package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.GetUserListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUserListUseCase(get()) }
}