package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.domain.use_case.GetAllChats
import com.vixiloc.vixgpt.domain.use_case.GetAllSettings
import com.vixiloc.vixgpt.domain.use_case.SendChat
import com.vixiloc.vixgpt.domain.use_case.UpdateSettings
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetAllChats(repository = get())
    }
    single {
        GetAllSettings(repository = get())
    }
    single {
        SendChat(repository = get())
    }
    single {
        UpdateSettings(repository = get())
    }
}