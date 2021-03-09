package com.picpay.desafio.android.data.remote.api

import com.picpay.desafio.android.data.model.UserApi
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserApi>>
}