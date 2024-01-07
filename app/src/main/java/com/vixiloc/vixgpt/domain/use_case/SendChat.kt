package com.vixiloc.vixgpt.domain.use_case

import com.aallam.openai.api.exception.OpenAIException
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.repository.OpenAiRepository
import com.vixiloc.vixgpt.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SendChat(
    private val repository: OpenAiRepository
) {
    operator fun invoke(
        chats: Chats,
        token: String
    ): Flow<Resource<Chats>> = flow {
        emit(Resource.Loading())
        try {
            val chatsResult = repository.sendMessage(chats, token)
            emit(Resource.Success(chatsResult))
        } catch (e: OpenAIException) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}