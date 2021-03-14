package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.MockFileReader
import com.picpay.desafio.android.data.base.BaseServiceTest
import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.remote.model.UserApi
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection

class PicPayServiceTest : BaseServiceTest() {

    @Test
    fun `given read user list file expected return success behavior`() {
        val reader = MockFileReader("GetUserListResponseSuccess.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `given read user empty list file expected return success behavior`() {
        val reader = MockFileReader("GetUserListResponseEmpty.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `given call user list service expected return list user api`() {

        val file = MockFileReader("GetUserListResponseSuccess.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(file.content)

        val expected = listOf(
            UserApi(1001, "username1", "name1", "https://fakeurl.com/photo1.png"),
            UserApi(1002, "username2", "name2", "https://fakeurl.com/photo2.png"),
            UserApi(1003, "username3", "name3", "https://fakeurl.com/photo3.png"),
            UserApi(1004, "username4", "name4", "https://fakeurl.com/photo4.png"),
            UserApi(1005, "username5", "name5", "https://fakeurl.com/photo5.png")
        )

        mockWebServer.enqueue(response)

        val testObserver = service.getUsers().test()

        testObserver.assertResult(expected)
        testObserver.assertNoErrors()
        testObserver.dispose()
    }

    @Test
    fun `given call user list service expected return empty list`() {

        val file = MockFileReader("GetUserListResponseEmpty.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(file.content)

        val expected = listOf<UserApi>()

        mockWebServer.enqueue(response)

        val testObserver = service.getUsers().test()

        testObserver.assertResult(expected)
        testObserver.assertNoErrors()
        testObserver.dispose()
    }

    @Test
    fun `given call user list service expected return error status`() {

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)

        mockWebServer.enqueue(response)

        val testObserver = service.getUsers().test()

        testObserver.assertError(HttpException::class.java)
        testObserver.dispose()
    }
}