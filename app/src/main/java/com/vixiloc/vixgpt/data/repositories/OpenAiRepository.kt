package com.vixiloc.vixgpt.data.repositories

import android.content.Context
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
import com.vixiloc.vixgpt.R
import com.vixiloc.vixgpt.data.sharedpreferences.ApiKey
import com.vixiloc.vixgpt.data.sharedpreferences.Model
import java.io.IOException
import kotlin.time.Duration.Companion.seconds

class OpenAiRepository(
    private val apiKey: ApiKey,
    private val model: Model,
    private val context: Context
) {

    @OptIn(BetaOpenAI::class)
    suspend fun sendCompletions(msg: String): ChatResult {
        if (apiKey.getApiKey() == null) {
            return ChatResult(
                false,
                context.getString(R.string.api_key_null)
            )
        } else {
            val config = OpenAIConfig(
                token = apiKey.getApiKey()!!,
                timeout = Timeout(socket = 60.seconds),
            )
            val openAi = OpenAI(config = config)
            try {
                val chatCompletionRequest = ChatCompletionRequest(
                    model = ModelId(model.getModel()!!), messages = listOf(
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

    fun setModel(newVal: String) = model.setModel(newVal)
    fun getModel(): String = model.getModel()!!
}


data class ChatResult(
    val success: Boolean,
    val msg: String
)