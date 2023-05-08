package com.aa.fakestore.data.repository


import com.aa.fakestore.data.model.AllCategories
import com.aa.fakestore.data.model.AllProducts
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface StoreRepository {

    fun getAllProducts(limit : Int ? = null): Observable<State<List<AllProductsItem>?>>
    fun getSingleProduct(productId: Int): Observable<State<AllProductsItem?>>
    fun getAllCategories(): Observable<State<AllCategories?>>
    fun getItemsInCategory(categoryName: String): Observable<State<List<AllProductsItem>>>
}