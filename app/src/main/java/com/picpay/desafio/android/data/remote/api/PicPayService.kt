package com.picpay.desafio.android.data.remote.api

import com.picpay.desafio.android.data.remote.model.UserApi
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers_OLD(): Call<List<UserApi>>

    @GET("users")
    fun getUsers(): Single<List<UserApi>>
}