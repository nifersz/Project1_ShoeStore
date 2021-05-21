package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListingViewModel : ViewModel() {

    var newShoe = MutableLiveData(Shoe())

    private val shoes = mutableListOf<Shoe>()
    private val _shoesList: MutableLiveData<MutableList<Shoe>> by lazy {
        MutableLiveData(mutableListOf())
    }
    val shoesList: LiveData<MutableList<Shoe>>; get() = _shoesList

    fun addShoe() {
        val shoeToAdd = newShoe.value
        if (shoeToAdd != null) {
            shoes.add(shoeToAdd)
            _shoesList.value = shoes
            newShoe.value = Shoe()
        }
    }

    fun clearShoeData() {
        newShoe.value = Shoe()
    }

    fun isShoeNameBlank() = newShoe.value?.name.isNullOrBlank()

}
