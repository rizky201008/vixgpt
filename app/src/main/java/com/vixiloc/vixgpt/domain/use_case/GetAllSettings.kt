package com.vixiloc.vixgpt.domain.use_case

import android.util.Log
import com.vixiloc.vixgpt.domain.model.Settings
import com.vixiloc.vixgpt.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetAllSettings(private val repository: SettingsRepository) {
    operator fun invoke(): Flow<Settings?> {
        Log.i("XLOG", "invokeSettings: ${repository.getSettings()}")
        return repository.getSettings()
    }
}