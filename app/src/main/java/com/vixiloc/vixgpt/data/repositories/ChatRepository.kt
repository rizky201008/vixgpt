package com.vixiloc.vixgpt.data.repositories

import android.content.Context
import com.vixiloc.vixgpt.data.dao.ChatDao
import com.vixiloc.vixgpt.data.db.ChatDatabase
import com.vixiloc.vixgpt.data.models.Chat
import com.vixiloc.vixgpt.data.sharedpreferences.ApiKey
import kotlinx.coroutines.flow.Flow

class ChatRepository(context: Context, private val apiKey: ApiKey) {
    private val chatDao: ChatDao

    init {
        val db = ChatDatabase.getDatabase(context)
        chatDao = db.chatDao()
    }

    var getChats: Flow<List<Chat>> = chatDao.getAllChats()

    fun insertChats(chat: Chat) = chatDao.addChat(chat)

    suspend fun disableAnimation() = chatDao.disableAllAnimation()

    fun getProcessChat(): Chat = chatDao.getProcessChat()

    suspend fun updateChat(chat: Chat) = chatDao.updateChat(chat)

    fun clearChats() = chatDao.clearAllChats()

    fun setApiKey(newVal: String) = apiKey.setApiKey(newVal)
}