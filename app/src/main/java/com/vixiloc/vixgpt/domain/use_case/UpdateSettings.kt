package com.vixiloc.vixgpt.domain.use_case

import com.vixiloc.vixgpt.domain.model.Settings
import com.vixiloc.vixgpt.domain.repository.SettingsRepository

class UpdateSettings(private val repository: SettingsRepository) {
    suspend operator fun invoke(data: Settings) {
        repository.updateSettings(data)
    }
}