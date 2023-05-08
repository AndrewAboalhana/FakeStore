package com.aa.fakestore.data.remote

import com.aa.fakestore.data.model.AllCategories
import com.aa.fakestore.data.model.AllProducts
import com.aa.fakestore.data.model.AllProductsItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreService {

    @GET("products")
    fun getAllProducts(
        @Query("limit") limit: Int? = null
    ):Single<Response<List<AllProductsItem>?>>

    @GET("products/{productId}")
    fun getSingleProduct(
        @Path("productId") productId:Int
    ):Single<Response<AllProductsItem?>>

    @GET("products/categories")
    fun getAllCategories():Single<Response<AllCategories?>>

    @GET("products/category/{category}")
    fun getItemsInCategory(
        @Path("category") categoryName:String
    ):Single<Response<List<AllProductsItem>>>
}