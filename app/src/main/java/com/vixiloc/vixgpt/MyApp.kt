package com.vixiloc.vixgpt

import android.app.Application
import com.vixiloc.vixgpt.di.databaseModule
import com.vixiloc.vixgpt.di.repositoryModule
import com.vixiloc.vixgpt.di.useCaseModule
import com.vixiloc.vixgpt.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule, repositoryModule, databaseModule,
                    useCaseModule
                )
            )
        }
    }
}