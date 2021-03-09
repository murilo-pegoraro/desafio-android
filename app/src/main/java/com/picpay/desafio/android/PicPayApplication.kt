package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.di.dataModules
import com.picpay.desafio.android.domain.di.domainModules
import com.picpay.desafio.android.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)
            modules(dataModules + domainModules + presentationModules)
        }
    }
}