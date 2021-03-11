package com.picpay.desafio.android.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserCache(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val username: String,
    val name: String,
    val img: String
)