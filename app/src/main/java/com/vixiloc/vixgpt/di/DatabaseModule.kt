package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.data.source.local.room.ChatsDao
import com.vixiloc.vixgpt.data.source.local.room.ChatsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory<ChatsDao> {
        ChatsDatabase.getDatabase(androidContext()).chatsDao()
    }
}