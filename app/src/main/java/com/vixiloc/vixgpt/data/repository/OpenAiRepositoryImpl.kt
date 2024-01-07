package com.vixiloc.vixgpt.data.repository

import android.util.Log
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.repository.OpenAiRepository
import kotlin.time.Duration.Companion.seconds

class OpenAiRepositoryImpl : OpenAiRepository {
    override suspend fun sendMessage(chats: Chats, token: String): Chats {
        val openAI = OpenAI(
            config = OpenAIConfig(
                timeout = Timeout(socket = 60.seconds),
                token = token
            )
        )
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(chats.model),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.User,
                    content = chats.question
                ),
            )
        )

        return openAI.chatCompletion(chatCompletionRequest).choices[0].let {
            Log.i("XLOG", "sendMessage: ${it.message.content}")
            chats.copy(answer = it.message.content ?: "")
        }
    }
}