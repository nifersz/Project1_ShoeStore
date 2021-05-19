package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentInstructionsBinding>(
            inflater, R.layout.fragment_instructions, container, false
        )
        binding.instructionsNextBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_fromInstructions_toListing
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle()
        hideFloatingButton()
    }

    private fun hideFloatingButton() {
        (activity as MainActivity).hideFAButton()
    }

    private fun updateToolbarTitle() {
        (activity as MainActivity).supportActionBar?.title = getString(
            R.string.instructions_title_for_toolbar
        )
    }

}
