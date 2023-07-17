package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.data.repositories.ChatRepository
import com.vixiloc.vixgpt.data.repositories.OpenAiRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ChatRepository(androidContext(), get())
    }

    single {
        OpenAiRepository(get(), get(),androidContext())
    }

}