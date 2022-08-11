package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {


    var shoeName: String = ""
    var shoeBrand: String = ""
    var shoeSize: Double = 0.0
    var shoeDescription: String = ""

    private var _shoesList: MutableLiveData<ArrayList<Shoe>>? = MutableLiveData<ArrayList<Shoe>>()
    val shoesList: LiveData<ArrayList<Shoe>> get() = _shoesList!!
    private var _fieldsState: MutableLiveData<Int?> = MutableLiveData<Int?>()
    val fieldsState: LiveData<Int?> get() = _fieldsState

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

    fun createShoe() {
        if (shoeName.isEmpty()) {
            _fieldsState.value = 1
        } else if (shoeBrand.isEmpty()) {
            _fieldsState.value = 2
        } else if (shoeSize == 0.0) {
            _fieldsState.value = 3
        } else if (shoeDescription.isEmpty()) {
            _fieldsState.value = 4
        } else {
            val newShoe = Shoe(shoeName, shoeSize, shoeBrand, shoeDescription)
            addToList(newShoe)
            resetFields()

        }
    }

    private fun addToList(shoe: Shoe) {
        _shoesList?.value?.add(shoe)
        _insertState.value = true
    }

    fun onDoneObservingAddState() {
        _insertState.value = null
    }
    fun onDoneObservingFieldsState() {
        _fieldsState.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _shoesList = null
    }

    private fun resetFields() {
        shoeName = ""
        shoeBrand = ""
        shoeSize = 0.0
        shoeDescription = ""
    }
}