package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe

class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding
    private val viewModel: ListingViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_listing, container, false
        )
        setFABOnClick()
        viewModel.shoesList.observe(viewLifecycleOwner, {
            onShoesListChanged(inflater, container, it)
        })
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

    private fun addShoeItemInListUi(
        inflater: LayoutInflater,
        container: ViewGroup?,
        shoe: Shoe
    ) {
        val shoeItemForList: ItemShoeBinding = DataBindingUtil.inflate(
            inflater, R.layout.item_shoe, container, false
        )
        shoeItemForList.shoeItemNameTxtv.text = shoe.name
        shoeItemForList.shoeItemCompanyTxtv.text = shoe.company
        shoeItemForList.shoeItemSizeTxtv.text = shoe.size.toString()
        if (shoe.description.isBlank()) {
            shoeItemForList.shoeItemDescTxtv.visibility = View.GONE
        } else {
            shoeItemForList.shoeItemDescTxtv.text = shoe.description
        }
        binding.listingShoesLinLay.addView(shoeItemForList.root)
    }

    private fun onShoesListChanged(
        inflater: LayoutInflater,
        container: ViewGroup?,
        listFound: MutableList<Shoe>
    ) {
        if (listFound.isEmpty()) {
            showEmptyListToast()
        } else listFound.forEach {
            addShoeItemInListUi(inflater, container, it)
        }
    }

    private fun setFABOnClick() {
        rootActivity().addFABClick {
            rootActivity().navigateTo(R.id.action_fromListing_toDetail)
        }
    }

    private fun showEmptyListToast() = Toast.makeText(
        requireActivity(), R.string.listing_begin_empty, Toast.LENGTH_LONG
    ).show()

    private fun showFloatingButton() {
        rootActivity().showFAButton()
    }

    private fun updateToolbarTitle() {
        rootActivity().supportActionBar?.title = getString(
            R.string.listing_title_for_toolbar
        )
    }

}
