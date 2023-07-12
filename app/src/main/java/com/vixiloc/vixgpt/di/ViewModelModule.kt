package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.presentation.viewmodels.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ChatViewModel(get(),get())
    }
}