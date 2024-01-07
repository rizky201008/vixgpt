package com.vixiloc.vixgpt.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chats(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "model") val model: String,
)
