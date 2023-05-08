package com.aa.fakestore.ui.homeFragment

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.RetrofitClient
import com.aa.fakestore.data.repository.StoreRepository
import com.aa.fakestore.data.repository.StoreRepositoryImpl
import com.aa.fakestore.ui.base.BaseViewModel
import com.aa.fakestore.utils.State

class HomeViewModel : BaseViewModel(), ProductListenerInteraction {

    private val repository: StoreRepository = StoreRepositoryImpl(RetrofitClient.apiService)

    @StringRes
    private val electronicsStringRec = R.string.Electronics

    @StringRes
    private val jeweleryStringRec = R.string.jewelery

    @StringRes
    private val menClothesStringRec = R.string.men_s_clothing

    @StringRes
    private val womenClothesStringRec = R.string.women_s_clothing

    private val _productsCollections: MutableLiveData<List<ProductsCollection>> =
        MutableLiveData(emptyList())
    val productsCollection: LiveData<List<ProductsCollection>> = _productsCollections

    private val _electronics = MutableLiveData<State<List<AllProductsItem>>>(State.Loading)
    val electronicsLiveData: LiveData<State<List<AllProductsItem>>> = _electronics

    private val _jewelery = MutableLiveData<State<List<AllProductsItem>>>(State.Loading)

    private val _menClothes = MutableLiveData<State<List<AllProductsItem>>>(State.Loading)

    private val _womenClothes = MutableLiveData<State<List<AllProductsItem>>>(State.Loading)

    init {
        getElectronics()
//        getJewelry()
//        getMenClothes()
//        getWomenClothes()
    }

    private fun getElectronics() {
        bindStateUpdates(
            repository.getItemsInCategory(categoryName = ELECTRONICS),
            ::handleElectronicsFailure,
            ::handleElectronicsState
        )
    }

    private fun handleElectronicsFailure(throwable: Throwable) {
        _electronics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleElectronicsState(state: State<List<AllProductsItem>>) {
        _electronics.postValue(state)
        if (state is State.Success) insertNewProductCollection(electronicsStringRec, state)
        Log.i("sdad",state.toData().toString())
    }


    private fun getJewelry() {
        bindStateUpdates(
            repository.getItemsInCategory(categoryName = JEWELERY),
            ::handleJeweleryFailure,
            ::handleJeweleryState
        )
    }

    private fun handleJeweleryFailure(throwable: Throwable) {
        _jewelery.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleJeweleryState(state: State<List<AllProductsItem>>) {
        _jewelery.postValue(state)
        if (state is State.Success) insertNewProductCollection(jeweleryStringRec, state)
    }

    private fun getMenClothes() {
        bindStateUpdates(
            repository.getItemsInCategory(categoryName = MEN_CLOTHES),
            ::handleMenClothesFailure,
            ::handleMenClothesState
        )
    }

    private fun handleMenClothesFailure(throwable: Throwable) {
        _menClothes.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleMenClothesState(state: State<List<AllProductsItem>>) {
        _menClothes.postValue(state)
        if (state is State.Success) insertNewProductCollection(menClothesStringRec, state)
    }

    private fun getWomenClothes() {
        bindStateUpdates(
            repository.getItemsInCategory(categoryName = WOMEN_CLOTHES),
            ::handleWomenClothesFailure,
            ::handleWomenClothesState
        )
    }

    private fun handleWomenClothesFailure(throwable: Throwable) {
        _womenClothes.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleWomenClothesState(state: State<List<AllProductsItem>>) {
        _womenClothes.postValue(state)
        if (state is State.Success) insertNewProductCollection(womenClothesStringRec, state)
    }

    private fun insertNewProductCollection(titleId: Int, products: State<List<AllProductsItem>>) {
        _productsCollections.value =
            _productsCollections.value!!.plus(ProductsCollection(titleId, products))
    }

    override fun onClick(item: AllProductsItem) {
    }

    override fun onTryAgainClicked() {
        getElectronics()
        getJewelry()
        getMenClothes()
        getWomenClothes()
    }

    companion object {
        private const val ELECTRONICS = "electronics"
        private const val JEWELERY = "jewelery"
        private const val MEN_CLOTHES = "men's clothing"
        private const val WOMEN_CLOTHES = "women's clothing"
        private const val UNKNOWN_ERROR = "Unknown error"
    }

}