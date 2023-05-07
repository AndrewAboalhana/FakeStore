package com.aa.fakestore.data.repository

import com.aa.fakestore.data.model.AllCategories
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.StoreService
import com.aa.fakestore.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response


class StoreRepositoryImpl(
private val storeServiceImpl: StoreService
): StoreRepository{

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Observable<State<T?>> {
        return function()
            .map { responseWrapper: Response<T> ->
                if (responseWrapper.isSuccessful) {
                    State.Success(responseWrapper.body())
                } else {
                    State.Failed(responseWrapper.message())
                }
            }.startWith(Observable.just(State.Loading))
    }

    override fun getAllProducts(): Observable<State<List<AllProductsItem>?>> {
       return wrapWithState { storeServiceImpl.getAllProducts() }
    }

    override fun getSingleProduct(productId: Int): Observable<State<AllProductsItem?>> {
       return wrapWithState { storeServiceImpl.getSingleProduct(productId) }
    }

    override fun getAllCategories(): Observable<State<AllCategories?>> {
        return wrapWithState{ storeServiceImpl.getAllCategories() }
    }

    override fun getItemsInCategory(categoryName: String): Observable<State<AllProductsItem?>> {
        return  wrapWithState { storeServiceImpl.getItemsInCategory(categoryName)}
    }
}