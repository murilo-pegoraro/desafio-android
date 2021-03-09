package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User
import io.reactivex.Single

interface UserRepository {
    fun getUsers(): Single<List<User>>
}