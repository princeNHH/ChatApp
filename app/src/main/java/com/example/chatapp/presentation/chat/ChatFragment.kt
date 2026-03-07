package com.example.chatapp.presentation.chat

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val viewModel: ChatViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private lateinit var messageInput: EditText
    private lateinit var sendButton: ImageButton

    private val chatId = "chat_1"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recyclerChat)
        messageInput = view.findViewById(R.id.edtMessage)
        sendButton = view.findViewById(R.id.btnSend)

        adapter = ChatAdapter()

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        recyclerView.adapter = adapter


        sendButton.setOnClickListener {

            val text = messageInput.text.toString()

            if (text.isNotBlank()) {

                viewModel.sendMessage(chatId, text)

                messageInput.setText("")

            }

        }

        observeMessages()

        viewModel.loadMessages(chatId)

    }


    private fun observeMessages() {

        lifecycleScope.launch {

            viewModel.messages.collect {

                adapter.submitList(it)

                recyclerView.scrollToPosition(it.size - 1)

            }

        }

    }

}