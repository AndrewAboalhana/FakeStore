package com.aa.fakestore.ui.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.ui.categoryFragment.CategoryInteractionListener
import com.aa.fakestore.utils.SingleEvent
import com.aa.fakestore.utils.State

class HomeViewModel : BaseViewModel(), CategoryInteractionListener {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)

    private val _navigationToDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToDetails: LiveData<SingleEvent<Int>> = _navigationToDetails

    private val _limitProducts: MutableLiveData<State<List<AllProductsItem>>> = MutableLiveData()
    val limitProducts: LiveData<State<List<AllProductsItem>>> = _limitProducts

    init {
        fetchLimitProducts()
    }




    private fun fetchLimitProducts() {
        bindStateUpdates(
            repository.getAllProducts(10),
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

    override fun goToDetails(productId: Int) {
        _navigationToDetails.postValue(SingleEvent(productId))
    }

}