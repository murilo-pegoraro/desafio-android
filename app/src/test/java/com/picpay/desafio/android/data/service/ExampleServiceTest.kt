package com.picpay.desafio.android.data.service

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.remote.model.UserApi
import com.picpay.desafio.android.data.remote.api.PicPayService
import com.picpay.desafio.android.data.service.ExampleService
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service =
        ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mock<Call<List<UserApi>>>()
        val expectedUsers = emptyList<UserApi>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers_OLD()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}