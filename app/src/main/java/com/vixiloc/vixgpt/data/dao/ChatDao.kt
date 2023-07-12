package com.vixiloc.vixgpt.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vixiloc.vixgpt.data.models.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query("SELECT * FROM chat")
    fun getAllChats(): Flow<List<Chat>>

    @Query("SELECT * FROM chat ORDER BY id DESC LIMIT 1")
    fun getLastChats(): Chat

    @Query("SELECT * FROM chat WHERE message = 'processing' LIMIT 1")
    fun getProcessChat(): Chat

    @Update
    suspend fun updateChat(chat: Chat)

    @Query("UPDATE chat set animated = 0 WHERE role = 2")
    suspend fun disableAllAnimation()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addChat(chat: Chat)

    @Query("DELETE from chat")
    fun clearAllChats()
}