package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import io.reactivex.Single

class GetUserListUseCase(private val userRepository: UserRepository) {

    fun getUserList(): Single<List<User>> = userRepository.getUsers()

}