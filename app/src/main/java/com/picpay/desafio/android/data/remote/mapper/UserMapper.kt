package com.picpay.desafio.android.data.remote.mapper

import com.picpay.desafio.android.data.model.UserApi
import com.picpay.desafio.android.domain.model.User


fun List<UserApi>.toDomain() = this.map { it.toDomain() }
fun UserApi.toDomain() = User(this.img, this.name, this.id, this.username)