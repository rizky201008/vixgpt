package com.vixiloc.vixgpt.presentation.settings

sealed class SettingsScreenEvent {
    data class EnteredApiKey(val apiKey: String) : SettingsScreenEvent()
    data class OpenSettingsSheet(val editedSetting: Int) : SettingsScreenEvent()
    object CloseSettingsSheet : SettingsScreenEvent()
    data class EnteredAiModel(val aiModel: String) : SettingsScreenEvent()

    object SubmitSettings : SettingsScreenEvent()
}