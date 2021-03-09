package com.picpay.desafio.android

import com.picpay.desafio.android.data.model.UserApi
import com.picpay.desafio.android.data.remote.api.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserApi> {
        val users = service.getUsers_OLD().execute()

        return users.body() ?: emptyList()
    }
}