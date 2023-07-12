package com.vixiloc.vixgpt.data.repositories

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.exception.OpenAIException
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.vixiloc.vixgpt.data.sharedpreferences.ApiKey
import java.io.IOException
import kotlin.time.Duration.Companion.seconds

class OpenAiRepository(private val apiKey: ApiKey) {

    @OptIn(BetaOpenAI::class)
    suspend fun SendCompletions(msg: String): ChatResult {
        if (apiKey.getApiKey() == null) {
            return ChatResult(
                false,
                "Please add your API Key from [OpenAi](https://platform.openai.com/account/api-keys) first by clicking settings button on top of this page"
            )
        } else {
            val config = OpenAIConfig(
                token = apiKey.getApiKey()!!,
                timeout = Timeout(socket = 60.seconds),
            )
            val openAi = OpenAI(config = config)
            try {
                val chatCompletionRequest = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"), messages = listOf(
                        ChatMessage(
                            role = ChatRole.User, content = msg
                        )
                    )
                )

                val completion: ChatCompletion = openAi.chatCompletion(chatCompletionRequest)
                val balasan: String = completion.choices[0].message?.content ?: ""
                return ChatResult(
                    true,
                    balasan
                )

            } catch (e: Exception) {
                return ChatResult(
                    false,
                    "$e"
                )
            } catch (e: IOException) {
                return ChatResult(
                    false,
                    "$e"
                )
            } catch (e: OpenAIException) {
                return ChatResult(
                    false,
                    "${e.message}"
                )
            }
        }
    }
}

data class ChatResult(
    val success: Boolean,
    val msg: String
)