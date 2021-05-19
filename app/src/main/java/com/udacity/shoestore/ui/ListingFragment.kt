package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingBinding

class ListingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentListingBinding>(
            inflater, R.layout.fragment_listing, container, false
        )
        setFABOnClick()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle()
        showFloatingButton()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_logout -> {
            rootActivity().navigateTo(R.id.action_fromListing_toLogin)
            true
        }
        else -> false
    }

    private fun rootActivity() = (activity as MainActivity)

    private fun setFABOnClick() {
        rootActivity().addFABClick {
            rootActivity().navigateTo(R.id.action_fromListing_toDetail)
        }
    }

    private fun showFloatingButton() {
        rootActivity().showFAButton()
    }

    private fun updateToolbarTitle() {
        rootActivity().supportActionBar?.title = getString(
            R.string.listing_title_for_toolbar
        )
    }

}
