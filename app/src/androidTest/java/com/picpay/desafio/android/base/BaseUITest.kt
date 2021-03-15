package com.picpay.desafio.android.base

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.picpay.desafio.android.di.remoteTestModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4ClassRunner::class)
abstract class BaseUITest : KoinTest {

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        loadKoinModules(remoteTestModule)
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        unloadKoinModules(remoteTestModule)
    }
}