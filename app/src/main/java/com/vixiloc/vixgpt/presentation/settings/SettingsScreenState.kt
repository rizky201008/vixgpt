package com.vixiloc.vixgpt.presentation.settings

data class SettingsScreenState(
    val apiKey: String = "",
    val openSettingsSheet: Boolean = false,
    val currentEditedSetting: Int = 0,
    val aiModel: String = "",
)