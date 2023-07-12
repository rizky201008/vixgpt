package com.vixiloc.vixgpt.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vixiloc.vixgpt.presentation.sreens.MyApp
import com.vixiloc.vixgpt.presentation.viewmodels.ChatViewModel
import com.vixiloc.vixgpt.ui.theme.VixGPTTheme
import org.koin.androidx.compose.getViewModel


class MainActivity : ComponentActivity() {
    private lateinit var chatViewModel: ChatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            chatViewModel = getViewModel()
            VixGPTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        chatViewModel.disableAnimation()
    }

    override fun onPause() {
        super.onPause()
        chatViewModel.disableAnimation()
    }
}