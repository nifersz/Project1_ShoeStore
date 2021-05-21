package com.udacity.shoestore.ui

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_UNSPECIFIED
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ListingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        initViews(binding)
        return binding.root
    }

    private fun initViews(binding: FragmentDetailBinding) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.clearShoeData()
        binding.shoeDescTxtInEtxt.setRawInputType(InputType.TYPE_CLASS_TEXT)
        binding.shoeDescTxtInEtxt.setOnEditorActionListener { view, actionId, _ ->
            descActionListener(view, actionId)
        }
        binding.shoeCancelBtn.setOnClickListener { view -> navigateToListing(view) }
        binding.shoeSaveBtn.setOnClickListener { view -> onSaveClicked(view) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle()
        hideFloatingButton()
    }

    private fun descActionListener(
        view: View,
        actionId: Int
    ) = if (actionId == IME_ACTION_UNSPECIFIED || actionId == IME_ACTION_DONE) {
        (activity as MainActivity).hideKeyboard()
        onSaveClicked(view)
        true
    } else false

    private fun hideFloatingButton() {
        (activity as MainActivity).hideFAButton()
    }

    private fun navigateToListing(view: View) {
        view.findNavController().navigateUp()
    }

    private fun onSaveClicked(view: View) {
        if (viewModel.isShoeNameBlank()) {
            showNotPossibleToCreateToast()
        } else {
            viewModel.addShoe()
            navigateToListing(view)
        }
    }

    private fun showNotPossibleToCreateToast() = Toast.makeText(
        requireActivity(), R.string.detail_not_possible_to_create, Toast.LENGTH_LONG
    ).show()

    private fun updateToolbarTitle() {
        (activity as MainActivity).supportActionBar?.title = getString(
            R.string.detail_title_for_toolbar
        )
    }

}
