package com.aa.fakestore.ui.testFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.utils.SingleEvent
import com.aa.fakestore.utils.State

class TestViewModel : BaseViewModel(), ProductInteractionListener {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)

    private val _navigationToDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToDetails: LiveData<SingleEvent<Int>> = _navigationToDetails


    private val _limitProducts: MutableLiveData<State<List<AllProductsItem>>> = MutableLiveData()
    val limitProducts: LiveData<State<List<AllProductsItem>>> = _limitProducts

    private val _categoryProducts: MutableLiveData<State<List<AllProductsItem>>> = MutableLiveData()
    val categoryProduct: LiveData<State<List<AllProductsItem>>> = _categoryProducts


    init {
        fetchLimitProducts()
        fetchAllProducts()
    }


    fun fetchAllProducts() {
        bindStateUpdates(
            repository.getAllProducts(null),
            ::handleCategoryProductsFailure,
            ::handleCategoryProductsState
        )
    }

    private fun fetchLimitProducts() {
        bindStateUpdates(
            repository.getAllProducts(5),
            ::handleProductsFailure,
            ::handleProductsState
        )
    }

    private fun handleProductsFailure(throwable: Throwable) {
        _limitProducts.postValue(State.Failed(throwable.message.toString()))
    }

    private fun handleProductsState(state: State<List<AllProductsItem>>) {
        _limitProducts.postValue(state)
    }


    fun fetchProductsInCategory(categoryName: String) {
        bindStateUpdates(
            repository.getItemsInCategory(categoryName),
            ::handleCategoryProductsFailure,
            ::handleCategoryProductsState
        )
    }

    private fun handleCategoryProductsFailure(throwable: Throwable) {
        _categoryProducts.postValue(State.Failed(throwable.message.toString()))
    }

    private fun handleCategoryProductsState(state: State<List<AllProductsItem>>) {
        _categoryProducts.postValue(state)
        Log.i("asffsa",_categoryProducts.value.toString())
    }

    override fun onClick(productId:Int) {
        _navigationToDetails.postValue(SingleEvent(productId))
    }

}