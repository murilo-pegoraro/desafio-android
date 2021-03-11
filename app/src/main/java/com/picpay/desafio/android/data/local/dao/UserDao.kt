package com.picpay.desafio.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.data.local.model.UserCache

@Dao
interface UserDao {

    @Insert
    fun insertAll(userList: List<UserCache>)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserCache>

    @Query("DELETE FROM user")
    fun deleteAll()
}