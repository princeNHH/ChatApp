package com.example.chatapp.presentation.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val viewModel: ChatViewModel by viewModels()
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val roomId = "roomId1"
    private val currentUserId: String?
        get() = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = currentUserId
        if(uid == null){
            findNavController().navigate(R.id.loginFragment)
            return
        }
        val adapter = ChatAdapter(uid)

        binding.recyclerChat.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        binding.btnSend.setOnClickListener {
            val text = binding.edtMessage.text.toString()

            if (text.isNotBlank()) {
                viewModel.sendMessage(roomId, uid, text)
                binding.edtMessage.setText("")
            }
        }

        observeMessages(adapter)
        viewModel.loadMessages(roomId)
    }


    private fun observeMessages(adapter: ChatAdapter) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.messages.collect {
                    adapter.submitList(it)
                    binding.recyclerChat.scrollToPosition(it.size - 1)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}