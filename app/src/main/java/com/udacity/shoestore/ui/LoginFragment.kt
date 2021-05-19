package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

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
        view.findNavController().navigate(R.id.action_fromLogin_toWelcome)
    }

    private fun updateToolbarTitle() {
        (activity as MainActivity).supportActionBar?.title = getString(
            R.string.login_title_for_toolbar
        )
    }

}
