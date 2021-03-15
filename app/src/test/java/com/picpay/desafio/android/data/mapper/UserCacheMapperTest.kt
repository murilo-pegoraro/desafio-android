package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.local.mapper.toCache
import com.picpay.desafio.android.data.local.mapper.toDomain
import com.picpay.desafio.android.data.local.model.UserCache
import com.picpay.desafio.android.domain.model.User
import org.junit.Assert
import org.junit.Test

class UserCacheMapperTest {

    @Test
    fun `given list of usercache object expected return list of user object`() {

        //arrange
        val expected = listOf(
            User(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            User(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            User(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            User(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            User(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        val userListMock = listOf(
            UserCache(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserCache(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserCache(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserCache(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserCache(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        //act
        val result = userListMock.toDomain()

        //assert
        Assert.assertEquals(result, expected)
    }

    @Test
    fun `given usercache object expected return user object`() {

        //arrange
        val expected = User(1000, "username", "name", "https://fakeurl.com/photo.png")
        val userCacheMock = UserCache(1000, "username", "name", "https://fakeurl.com/photo.png")

        //act
        val result = userCacheMock.toDomain()

        //assert
        Assert.assertEquals(result, expected)
    }

    @Test
    fun `given list of user object expected return list of usercache object`() {

        //arrange
        val expected = listOf(
            UserCache(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserCache(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserCache(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserCache(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserCache(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        val userListMock = listOf(
            User(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            User(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            User(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            User(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            User(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        //act
        val result = userListMock.toCache()

        //assert
        Assert.assertEquals(result, expected)
    }

    @Test
    fun `given user object expected return usercache object`() {

        //arrange
        val expected = UserCache(1000, "username", "name", "https://fakeurl.com/photo.png")
        val userMock = User(1000, "username", "name", "https://fakeurl.com/photo.png")

        //act
        val result = userMock.toCache()

        //assert
        Assert.assertEquals(result, expected)
    }
}