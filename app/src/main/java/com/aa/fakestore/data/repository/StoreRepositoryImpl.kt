package com.aa.fakestore.data.repository

import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.data.remote.StoreService
import com.aa.fakestore.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response


class StoreRepositoryImpl(
    private val storeServiceImpl: StoreService,
) : StoreRepository {

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Observable<State<T>> {
        return function()
            .map<State<T>> { response ->
                if (response.isSuccessful) {
                    State.Success(response.body()!!)
                } else {
                    State.Failed(response.message())
                }
            }
            .onErrorReturn { State.Failed(it.message ?: "Unknown error") }
            .startWith(Observable.just(State.Loading))
    }

    override fun getAllProducts(limit: Int?): Observable<State<List<AllProductsItem>>> {
        return wrapWithState { storeServiceImpl.getAllProducts(limit) }
    }

    override fun getSingleProduct(productId: Int): Observable<State<AllProductsItem>> {
        return wrapWithState { storeServiceImpl.getSingleProduct(productId) }
    }

    override fun getItemsInCategory(categoryName: String): Observable<State<List<AllProductsItem>>> {
        return wrapWithState { storeServiceImpl.getItemsInCategory(categoryName) }
    }
}