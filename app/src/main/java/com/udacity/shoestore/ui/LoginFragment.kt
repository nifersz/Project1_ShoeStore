package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var emailField: TextInputEditText
    private lateinit var passwordField: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        )
        initViews(binding)
        return binding.root
    }

    private fun initViews(binding: FragmentLoginBinding) {
        emailField = binding.loginEmailTxtInEtxt
        passwordField = binding.loginPasswordTxtInEtxt
        binding.loginPasswordTxtInEtxt.setOnEditorActionListener { view, actionId, _ ->
            pwdActionListener(view, actionId)
        }
        binding.loginCreateNewLoginBtn.setOnClickListener { view -> navigateToWelcome(view) }
        binding.loginSignInBtn.setOnClickListener { view -> navigateToWelcome(view) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle()
        hideFloatingButton()
    }

    private fun hideFloatingButton() {
        (activity as MainActivity).hideFAButton()
    }

    private fun navigateToWelcome(view: View) {
        emailField.setText("")
        passwordField.setText("")
        view.findNavController().navigate(R.id.action_fromLogin_toWelcome)
    }

    private fun pwdActionListener(
        view: View,
        actionId: Int
    ) = if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
        (activity as MainActivity).hideKeyboard()
        navigateToWelcome(view)
        true
    } else false

    private fun updateToolbarTitle() {
        (activity as MainActivity).supportActionBar?.title = getString(
            R.string.login_title_for_toolbar
        )
    }

}
