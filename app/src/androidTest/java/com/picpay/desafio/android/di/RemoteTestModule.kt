package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.remote.api.PicPayService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val MOCK_HOST_URL = "http://127.0.0.1:8080"

val remoteTestModule = module {
    single(override = true) { createTestWebService<PicPayService>(get()) }
}

inline fun <reified T> createTestWebService(okHttpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(MOCK_HOST_URL)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}