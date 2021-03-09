package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.remote.api.PicPayService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 20L
private const val READ_TIMEOUT = 20L
private const val WRITE_TIMEOUT = 20L

val RemoteModule = module {
    single { createWebService<PicPayService>(get()) }
    single { providesOkHttpClient() }

}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()
}