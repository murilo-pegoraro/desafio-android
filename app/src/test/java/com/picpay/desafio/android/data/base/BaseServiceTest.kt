package com.picpay.desafio.android.data.base

import com.google.gson.Gson
import com.picpay.desafio.android.data.remote.api.PicPayService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseServiceTest {

    lateinit var mockWebServer: MockWebServer

    lateinit var service: PicPayService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(PicPayService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}