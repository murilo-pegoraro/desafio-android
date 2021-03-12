package com.picpay.desafio.android.local.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.data.local.AppDataBase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.model.UserCache
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class UserDaoTest {

    private lateinit var database: AppDataBase

    private lateinit var dao: UserDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDataBase::class.java
        ).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun given_insert_list_of_usercache_object_expected_return_same_list_of_usercache_object() {

        //arrange
        val expected = listOf(
            UserCache(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserCache(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserCache(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserCache(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserCache(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )
        val userList = listOf(
            UserCache(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserCache(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserCache(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserCache(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserCache(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        //act
        dao.insertAll(userList)
        val result = dao.getAll()

        //assert
        Assert.assertEquals(result, expected)
    }

    @Test
    fun given_insert_list_of_usercache_object_expected_return_empty_list_of_usercache_object() {

        //arrange
        val expected = emptyList<UserCache>()
        val userList = listOf(
            UserCache(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserCache(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserCache(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserCache(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserCache(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        //act
        dao.insertAll(userList)
        dao.deleteAll()
        val result = dao.getAll()

        //assert
        Assert.assertEquals(result, expected)
    }
}