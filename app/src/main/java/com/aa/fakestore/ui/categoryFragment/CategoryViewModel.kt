package com.aa.fakestore.ui.categoryFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.utils.SingleEvent
import com.aa.fakestore.utils.State

class CategoryViewModel: BaseViewModel(), CategoryInteractionListener {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)

    private val _navigationToDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToDetails: LiveData<SingleEvent<Int>> = _navigationToDetails

    private val _categoryProducts: MutableLiveData<State<List<AllProductsItem>>> = MutableLiveData()
    val categoryProduct: LiveData<State<List<AllProductsItem>>> = _categoryProducts

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

    }

    override fun goToDetails(productId: Int) {
        _navigationToDetails.postValue(SingleEvent(productId))
    }
}