package com.aa.fakestore.data.repository


import com.aa.fakestore.data.model.AllCategories
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.utils.State
import io.reactivex.rxjava3.core.Observable

interface StoreRepository {

    fun getAllProducts(limit: Int?): Observable<State<List<AllProductsItem>>>
    fun getSingleProduct(productId: Int): Observable<State<AllProductsItem>>
    fun getItemsInCategory(categoryName: String): Observable<State<List<AllProductsItem>>>
    fun getAllCategories():Observable<State<AllCategories>>
}