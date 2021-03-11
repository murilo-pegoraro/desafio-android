package com.picpay.desafio.android.data.local.mapper

import com.picpay.desafio.android.data.local.model.UserCache
import com.picpay.desafio.android.domain.model.User


fun List<UserCache>.toDomain() = this.map { it.toDomain() }
fun UserCache.toDomain() = User(this.id, this.username, this.name, this.img)

fun List<User>.toCache() = this.map { it.toCache() }
fun User.toCache() = UserCache(this.id, this.username, this.name, this.img)