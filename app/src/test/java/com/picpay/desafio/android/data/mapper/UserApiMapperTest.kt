package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.remote.mapper.toDomain
import com.picpay.desafio.android.data.remote.model.UserApi
import com.picpay.desafio.android.domain.model.User
import org.junit.Assert
import org.junit.Test

class UserApiMapperTest {

    @Test
    fun `given list of userapi object expected return list of user object`() {

        //arrange
        val expected = listOf(
            User(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            User(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            User(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            User(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            User(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        val userListMock = listOf(
            UserApi(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserApi(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserApi(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserApi(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserApi(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        //act
        val result = userListMock.toDomain()

        //assert
        Assert.assertEquals(result, expected)
    }

    @Test
    fun `given userapi object expected return user object`() {

        //arrange
        val expected = User(1000, "username", "name", "https://fakeurl.com/photo.png")
        val userApiMock = UserApi(1000, "username", "name", "https://fakeurl.com/photo.png")

        //act
        val result = userApiMock.toDomain()

        //assert
        Assert.assertEquals(result, expected)
    }
}