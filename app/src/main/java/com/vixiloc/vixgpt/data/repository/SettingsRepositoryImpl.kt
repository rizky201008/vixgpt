package com.vixiloc.vixgpt.data.repository

import com.vixiloc.vixgpt.data.source.local.room.ChatsDao
import com.vixiloc.vixgpt.domain.model.Settings
import com.vixiloc.vixgpt.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(private val settingsDao: ChatsDao) : SettingsRepository {
    override fun getSettings(): Flow<Settings?> {
        return settingsDao.getAllSettings()
    }

    override suspend fun updateSettings(settings: Settings) {
        settingsDao.updateSettings(settings)
    }
}