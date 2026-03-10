package com.example.chatapp.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.model.ChatRoom
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.model.UserStatus
import com.example.chatapp.domain.usecase.ObserveMessageUseCase
import com.example.chatapp.domain.usecase.SendMessageUseCase
import com.example.chatapp.domain.usecase.OnlineStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeMessageUseCase: ObserveMessageUseCase,
    private val onlineStatusUseCase: OnlineStatusUseCase,
) : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages

    private val _useStatus = MutableStateFlow<UserStatus?>(null)
    val userStatus = _useStatus

    private val _room = MutableStateFlow<List<ChatRoom>>(emptyList())
    val room = _room

    private val _use = MutableStateFlow<List<User>>(emptyList())
    val use = _use


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

    fun observeStatusUser(uid: String) {
        viewModelScope.launch {
            onlineStatusUseCase.observeStatus(uid).collect {
                _useStatus.value = it
            }
        }
    }
}