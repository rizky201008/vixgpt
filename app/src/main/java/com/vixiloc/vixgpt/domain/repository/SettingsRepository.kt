package com.vixiloc.vixgpt.domain.repository

import com.vixiloc.vixgpt.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<Settings?>

    suspend fun updateSettings(settings: Settings)
}