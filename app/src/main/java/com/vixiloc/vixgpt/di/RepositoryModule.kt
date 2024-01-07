package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.data.repository.ChatsRepositoryImpl
import com.vixiloc.vixgpt.data.repository.OpenAiRepositoryImpl
import com.vixiloc.vixgpt.data.repository.SettingsRepositoryImpl
import com.vixiloc.vixgpt.domain.repository.ChatsRepository
import com.vixiloc.vixgpt.domain.repository.OpenAiRepository
import com.vixiloc.vixgpt.domain.repository.SettingsRepository
import org.koin.dsl.module


val repositoryModule = module {
    factory<ChatsRepository> {
        ChatsRepositoryImpl(dao = get())
    }

    factory<OpenAiRepository> {
        OpenAiRepositoryImpl()
    }

    factory<SettingsRepository> {
        SettingsRepositoryImpl(settingsDao = get())
    }
}