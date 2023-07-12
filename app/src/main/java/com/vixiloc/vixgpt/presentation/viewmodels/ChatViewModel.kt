package com.vixiloc.vixgpt.presentation.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixgpt.data.models.Chat
import com.vixiloc.vixgpt.data.repositories.ChatRepository
import com.vixiloc.vixgpt.data.repositories.OpenAiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val openAiRepository: OpenAiRepository
) : ViewModel() {

    val chats: Flow<List<Chat>> get() = chatRepository.getChats

    var message by mutableStateOf("")
        private set

    var messageError by mutableStateOf(false)
        private set

    var apikey by mutableStateOf("")
        private set

    var apikeyError by mutableStateOf(false)
        private set

    var dialog by mutableStateOf(false)
        private set

    fun updateMessageState(newValue: String) {
        message = newValue
    }

    fun updateApiKeyState(newValue: String) {
        apikey = newValue
    }

    var enableMessageField by mutableStateOf(true)
        private set

    fun sendMessage() {
        enableMessageField = false
        if (message.isNotBlank() && enableMessageField == false) {
            messageError = false
            val chat = Chat(message = message, role = 1, animated = false)
            val chat1 = Chat(message = "processing", role = 2, animated = false)
            viewModelScope.launch {
                chatRepository.insertChats(
                    chat
                )
                chatRepository.insertChats(
                    chat1
                )

                val pendingChat = chatRepository.getProcessChat()

                val sendMsg = openAiRepository.SendCompletions(message)

                when (sendMsg.success) {
                    false -> {
                        enableMessageField = true
                        chatRepository.updateChat(
                            Chat(
                                id = pendingChat.id,
                                message = sendMsg.msg,
                                role = 2,
                                animated = true
                            )
                        )
                    }

                    true -> {
                        enableMessageField = true
                        chatRepository.updateChat(
                            Chat(
                                id = pendingChat.id,
                                message = sendMsg.msg,
                                role = 2,
                                animated = true
                            )
                        )
                    }
                }
            }
        } else {
            messageError = true
        }
    }

    fun disableAnimation() {
        viewModelScope.launch {
            chatRepository.disableAnimation()
        }
    }

    fun clearChats() = chatRepository.clearChats()

    fun showDialog() {
        dialog = !dialog
    }

    fun setApiKey() {
        if (apikey.isNotBlank()) {
            apikeyError = false
            showDialog()
            chatRepository.setApiKey(apikey)
        } else {
            apikeyError = true
        }
    }

}