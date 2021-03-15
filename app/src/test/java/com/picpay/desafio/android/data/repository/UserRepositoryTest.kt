package com.picpay.desafio.android.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.remote.model.UserApi
import com.picpay.desafio.android.domain.model.User
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

class UserRepositoryTest {

    private lateinit var repository: UserRepositoryImpl

    @Mock
    private lateinit var service: PicPayService

    @Mock
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = UserRepositoryImpl(service, dao)
    }

    @Test
    fun `given get user list call expected return success behavior`() {

        //arrange
        val response = listOf(
            UserApi(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserApi(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserApi(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserApi(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserApi(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )
        val expected = listOf(
            User(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            User(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            User(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            User(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            User(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        whenever(service.getUsers()).thenReturn(Single.just(response))

        //act
        val testObserver = repository.getUsers().test()

        //assert
        verify(dao, times(1)).deleteAll()
        verify(dao, times(1)).insertAll(any())

        testObserver.assertNoErrors()
        testObserver.assertResult(expected)
        testObserver.dispose()
    }

    @Test
    fun `given get user list call expected return error behavior`() {

        //arrange
        whenever(service.getUsers()).thenReturn(Single.error(IOException()))

        //act
        val testObserver = repository.getUsers().test()

        //assert
        verify(dao, times(1)).getAll()

        testObserver.assertNoErrors()
        testObserver.dispose()
    }
}