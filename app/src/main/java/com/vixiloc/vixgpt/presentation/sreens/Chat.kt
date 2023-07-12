package com.vixiloc.vixgpt.presentation.sreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vixiloc.vixgpt.presentation.components.ChatBubble
import com.vixiloc.vixgpt.presentation.components.ChatInput
import com.vixiloc.vixgpt.presentation.components.MainTopApBar
import com.vixiloc.vixgpt.presentation.components.Role
import com.vixiloc.vixgpt.presentation.viewmodels.ChatViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(navController: NavHostController) {
    val viewModel: ChatViewModel = getViewModel()
    val scope = rememberCoroutineScope()
    val chats by viewModel.chats.collectAsState(initial = null)
    val message = viewModel.message
    val apiKey = viewModel.apikey
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        MainTopApBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary
                ),
            onClear = {
                scope.launch {
                    viewModel.clearChats()
                }
            },
            onSetting = {
                scope.launch {
                    viewModel.showDialog()
                }
            }
        )
    }) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = rememberLazyListState(),
            ) {
                items(chats ?: emptyList()) { chats ->
                    ChatBubble(
                        role = if (chats.role == 1) Role.USER else Role.ASSISTANCE,
                        modifier = Modifier,
                        text = chats.message,
                        animated = chats.animated,
                        animationEnded = {
                            scope.launch {
                                viewModel.disableAnimation()
                            }
                        }
                    )
                }
            }
            ChatInput(
                value = message,
                onChange = { newValue ->
                    scope.launch {
                        viewModel.updateMessageState(newValue)
                    }
                },
                onButtonClick = {
                    scope.launch {
                        viewModel.sendMessage()
                        viewModel.updateMessageState("")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = viewModel.enableMessageField
            )

            if (viewModel.dialog) {
                AlertDialog(
                    onDismissRequest = { scope.launch { viewModel.showDialog() } },
                    title = { Text(text = "Setup API Key") },
                    text = {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                value = apiKey,
                                onValueChange = { scope.launch { viewModel.updateApiKeyState(it) } },
                                isError = viewModel.apikeyError,
                                trailingIcon = {
                                    Icon(
                                        imageVector = if (viewModel.apikeyError) Icons.Default.Error else Icons.Default.Key,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                visualTransformation = PasswordVisualTransformation()
                            )
                            Text(text = "Before you can using this application you must add your OpenAI Api key / Token. Your Api Key / Token is saved on your device.")
                        }
                    },
                    shape = MaterialTheme.shapes.large,
                    dismissButton = {
                        Button(onClick = { scope.launch { viewModel.showDialog() } }) {
                            Text(text = "Remind me later!")
                        }
                    },
                    confirmButton = {
                        Button(onClick = { scope.launch { viewModel.setApiKey() } }) {
                            Text(text = "Save")
                        }
                    }
                )
            }
        }
    }
}