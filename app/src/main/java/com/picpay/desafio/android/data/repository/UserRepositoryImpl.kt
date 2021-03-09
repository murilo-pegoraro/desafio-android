package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.remote.mapper.toDomain
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val service: PicPayService
) : UserRepository {

    override fun getUsers(): Single<List<User>> {
        return service.getUsers().map { it.toDomain() }
    }
}