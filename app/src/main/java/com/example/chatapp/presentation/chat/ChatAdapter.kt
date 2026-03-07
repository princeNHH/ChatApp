package com.example.chatapp.presentation.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.domain.model.Message

class ChatAdapter :
    ListAdapter<Message, ChatAdapter.ChatViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)

        return ChatViewHolder(view)

    }


    override fun onBindViewHolder(
        holder: ChatViewHolder,
        position: Int
    ) {

        holder.bind(getItem(position))

    }


    class ChatViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val text: TextView =
            itemView.findViewById(R.id.txtMessage)

        fun bind(message: Message) {

            text.text = message.message

        }

    }


    class DiffCallback : DiffUtil.ItemCallback<Message>() {

        override fun areItemsTheSame(
            oldItem: Message,
            newItem: Message
        ): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: Message,
            newItem: Message
        ): Boolean {

            return oldItem == newItem

        }

    }

}