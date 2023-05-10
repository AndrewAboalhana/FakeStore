package com.aa.fakestore.ui.detailsFragment

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.utils.SingleEvent
import com.aa.fakestore.utils.State

class DetailsViewModel:BaseViewModel() {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)

    private val _navigationToCart: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCart: LiveData<SingleEvent<Int>> = _navigationToCart

    private val _product: MutableLiveData<State<AllProductsItem>> = MutableLiveData()
    val product: LiveData<State<AllProductsItem>> = _product


    fun fetchData(productId:Int){
        bindStateUpdates(
            repository.getSingleProduct(productId),
            ::onGetProductDetailsError,
            ::onGetProductDetailsSuccess
        )
    }

    private fun onGetProductDetailsSuccess(state: State<AllProductsItem>) {
        _product.postValue(state)
    }

    private fun onGetProductDetailsError(error: Throwable) {
        _product.postValue(State.Failed(error.message.toString()))
    }


}