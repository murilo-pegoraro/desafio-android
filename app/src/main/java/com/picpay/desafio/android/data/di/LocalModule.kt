package com.picpay.desafio.android.data.di

import androidx.room.Room
import com.picpay.desafio.android.data.local.AppDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "app-db")
            .build()
    }

    single { get<AppDataBase>().userDao() }
}