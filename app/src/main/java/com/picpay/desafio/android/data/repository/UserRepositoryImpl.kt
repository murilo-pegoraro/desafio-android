package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.mapper.toCache
import com.picpay.desafio.android.data.local.mapper.toDomain
import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.remote.mapper.toDomain
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val service: PicPayService,
    private val userCache: UserDao
) : UserRepository {

    override fun getUsers(): Single<List<User>> {
        return service.getUsers().map {
            val userList = it.toDomain()
            userCache.deleteAll()
            userCache.insertAll(userList.toCache())
            userList
        }.onErrorReturn {
            userCache.getAll().map { userCache -> userCache.toDomain() }
        }
    }
}