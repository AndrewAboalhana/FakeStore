package com.aa.fakestore.ui.categoriesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.data.model.AllCategories
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.utils.SingleEvent
import com.aa.fakestore.utils.State

class CategoriesViewModel: BaseViewModel(), CategoriesInteractionListener {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)

    private val _navigationToDetails: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    val navigationToDetails: LiveData<SingleEvent<String>> = _navigationToDetails

    private val _categories:MutableLiveData<State<AllCategories>> = MutableLiveData()
    val categories: LiveData<State<AllCategories>> = _categories


    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        bindStateUpdates(
            repository.getAllCategories(),
            ::handleProductsFailure,
            ::handleProductsState
        )
    }

    private fun handleProductsFailure(throwable: Throwable) {
        _categories.postValue(State.Failed(throwable.message.toString()))
    }

    private fun handleProductsState(state: State<AllCategories>) {
        _categories.postValue(state)
    }

    override fun itemsInCategory(categoryName: String) {
        _navigationToDetails.postValue(SingleEvent(categoryName))
    }


}