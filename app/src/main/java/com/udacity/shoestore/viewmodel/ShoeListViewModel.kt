package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private var _shoesList: MutableLiveData<ArrayList<Shoe>>? = MutableLiveData<ArrayList<Shoe>>()
    val shoesList: LiveData<ArrayList<Shoe>> get() = _shoesList!!

    private var _insertState = MutableLiveData<Boolean?>()
    val insertState: LiveData<Boolean?> get() = _insertState

    init {
        _shoesList?.value = populateList()
    }

    private fun populateList(): ArrayList<Shoe> {
        val list = arrayListOf<Shoe>()
        list.apply {
            add(
                Shoe(
                    "Desert F18", 42.0, "Desert",
                    "Comfortable Canvas upper material\nSimply Slip-On and off\nSoft footbed ensures all-day comfort\nSturdy PVC outsole offers added comfort"
                )
            )
            add(
                Shoe(
                    "Adidas EQ19 RUN GV7373", 45.0, "Adodas",
                    "Textile upper with TPU support\nHighly breathable feel"
                )
            )

        }
        return list
    }

    fun createShoe(name: String, brand: String, size: Double, description: String) {
        val newShoe = Shoe(name, size, brand, description)
        addToList(newShoe)
    }

    private fun addToList(shoe: Shoe) {
        _shoesList?.value?.add(shoe)
        _insertState.value = true
    }

    fun onDoneObservingAddState() {
        _insertState.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _shoesList = null
    }
}