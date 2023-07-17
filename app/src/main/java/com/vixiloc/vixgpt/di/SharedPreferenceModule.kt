package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.data.sharedpreferences.ApiKey
import com.vixiloc.vixgpt.data.sharedpreferences.Model
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefModule = module {
    single {
        ApiKey(androidContext())
    }
    single {
        Model(androidContext())
    }
}