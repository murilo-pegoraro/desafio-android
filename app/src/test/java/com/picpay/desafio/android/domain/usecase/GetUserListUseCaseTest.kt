package com.picpay.desafio.android.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.model.User
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

class GetUserListUseCaseTest {

    private lateinit var useCase: GetUserListUseCase

    @Mock
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetUserListUseCase(repository)
    }

    @Test
    fun `given get user list call expected return success behavior`() {

        //arrange
        val response = listOf(
            User(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            User(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            User(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            User(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            User(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )
        val expected = listOf(
            User(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            User(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            User(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            User(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            User(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        whenever(repository.getUsers()).thenReturn(Single.just(response))

        //act
        val testObserver = useCase.getUserList().test()

        //assert
        testObserver.assertNoErrors()
        testObserver.assertResult(expected)
        testObserver.dispose()
    }

    @Test
    fun `given get user list call expected return error behavior`() {

        //arrange
        val error = IOException()
        whenever(repository.getUsers()).thenReturn(Single.error(error))

        //act
        val testObserver = useCase.getUserList().test()

        //assert
        testObserver.assertError(error)
        testObserver.dispose()
    }

    @Test
    fun `given get user list call expected return empty behavior`() {

        //arrange
        whenever(repository.getUsers()).thenReturn(Single.just(emptyList()))

        //act
        val testObserver = useCase.getUserList().test()

        //assert
        testObserver.assertNoErrors()
        testObserver.assertResult(emptyList())
        testObserver.dispose()
    }
}