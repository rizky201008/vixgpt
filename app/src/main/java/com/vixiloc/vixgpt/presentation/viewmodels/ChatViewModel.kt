package com.vixiloc.vixgpt.presentation.viewmodels

import androidx.compose.runtime.State
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

    private var messageError by mutableStateOf(false)

    private val _apikey = mutableStateOf("")
    val apikey: State<String> get() = _apikey

    private val _model = mutableStateOf("")
    val model: State<String> get() = _model

    var apikeyError by mutableStateOf(false)
        private set
    var modelError by mutableStateOf(false)
        private set

    var dialog by mutableStateOf(false)
        private set

    fun updateMessageState(newValue: String) {
        message = newValue
    }

    fun updateModelState(newValue: String) {
        _model.value = newValue
    }

    fun updateApiKeyState(newValue: String) {
        _apikey.value = newValue
    }

    var enableMessageField by mutableStateOf(true)
        private set

    fun sendMessage() {
        if (message.isNotBlank()) {
            enableMessageField = false
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

                val sendMsg = openAiRepository.sendCompletions(message)

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

    fun updateSetting() {
        if (apikey.value.isBlank()) {
            apikeyError = true
        } else if (model.value.isBlank()) {
            modelError = true
        } else {
            apikeyError = false
            modelError = false
            showDialog()
            chatRepository.setApiKey(apikey.value)
            openAiRepository.setModel(model.value)
        }
    }

    private fun getSetting() {
        _apikey.value = chatRepository.getApiKey()
        _model.value = openAiRepository.getModel()
    }

    init {
        getSetting()
    }

}