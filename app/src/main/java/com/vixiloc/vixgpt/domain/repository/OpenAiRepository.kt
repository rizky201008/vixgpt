package com.vixiloc.vixgpt.domain.repository

import com.vixiloc.vixgpt.domain.model.Chats

interface OpenAiRepository {
    suspend fun sendMessage(chats: Chats, token: String): Chats
}