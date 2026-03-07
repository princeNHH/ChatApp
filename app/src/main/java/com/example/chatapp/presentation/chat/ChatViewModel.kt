package com.example.chatapp.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.usecase.GetMessageUseCase
import com.example.chatapp.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
) : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages

    fun loadMessages(chatId: String) {
        viewModelScope.launch {
            getMessageUseCase(chatId).collect { messages ->
                _messages.value = messages
            }
        }
    }

    fun sendMessage(chatId: String, text: String) {
        val message = Message(
            chatId = chatId,
            senderId = "currentUser",
            message = text,
            timestamp = System.currentTimeMillis()
        )
        viewModelScope.launch {
            sendMessageUseCase(message)
        }
    }
}