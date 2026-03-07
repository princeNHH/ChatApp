package com.example.chatapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        emailInput = view.findViewById(R.id.edtEmail)
        passwordInput = view.findViewById(R.id.edtPassword)
        loginButton = view.findViewById(R.id.btnLogin)
        progress = view.findViewById(R.id.progress)

        loginButton.setOnClickListener {

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            viewModel.login(email, password)

        }

        observeLogin()

    }

    private fun observeLogin() {

        lifecycleScope.launch {

            viewModel.loginState.collect { state ->

                when (state) {

                    is LoginState.Idle -> {

                        progress.visibility = View.GONE

                    }

                    is LoginState.Loading -> {

                        progress.visibility = View.VISIBLE

                    }

                    is LoginState.Success -> {

                        progress.visibility = View.GONE

                        openHome()

                    }

                    is LoginState.Error -> {

                        progress.visibility = View.GONE

                        Toast.makeText(
                            requireContext(),
                            state.message ?: "Login error",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }

            }

        }

    }

    private fun openHome() {

        findNavController()
            .navigate(R.id.action_login_to_chat)

    }

}