package com.vixiloc.vixgpt.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chat(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "role")
    val role: Int,

    @ColumnInfo(name = "animated")
    val animated: Boolean = false
)