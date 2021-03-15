package com.picpay.desafio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.model.UserCache

@Database(version = 1, entities = [UserCache::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}