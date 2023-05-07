package com.aa.fakestore.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.utils.State

class TestViewModel :BaseViewModel() {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)
    private val _products: MutableLiveData<State<List<AllProductsItem>>> = MutableLiveData()
    val products: LiveData<State<List<AllProductsItem>>> = _products

    init {
        getAllProducts()
    }

    private fun getAllProducts(){
     bindStateUpdates(repository.getAllProducts(),
         onNext = ::onGetSeriesDetailsSuccess,
         onError = ::onGetSeriesDetailsError
     )
    }

    private fun onGetSeriesDetailsSuccess(state: State<List<AllProductsItem>?>) {
        _products.postValue(State.Loading)
        state.toData()?.let {
            _products.postValue(State.Success(it))
            Log.i("TestViewModel",State.Success(it).toData().toString())
        }
    }

    private fun onGetSeriesDetailsError(error: Throwable) {
        _products.postValue(State.Failed(error.message.toString()))
    }

}