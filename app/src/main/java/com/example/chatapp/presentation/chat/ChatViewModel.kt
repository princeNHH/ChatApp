package com.example.chatapp.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.usecase.ObserveMessageUseCase
import com.example.chatapp.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeMessageUseCase: ObserveMessageUseCase
) : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages

    fun loadMessages(roomId: String) {
        viewModelScope.launch {
            observeMessageUseCase(roomId).collect {
                _messages.value = it
            }
        }
    }

    fun sendMessage(
        roomId: String,
        senderId: String,
        text: String
    ) {
        viewModelScope.launch {
            sendMessageUseCase(
                roomId,
                Message(
                    senderId = senderId,
                    text = text,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}