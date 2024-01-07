package com.vixiloc.vixgpt.domain.use_case

import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.repository.ChatsRepository

class InsertChat(private val repository: ChatsRepository) {
    suspend operator fun invoke(data: Chats){
        repository.insert(data)
    }
}