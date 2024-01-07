package com.vixiloc.vixgpt.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "api_key") val apiKey: String?,
    @ColumnInfo(name = "model") val model: String?
)
