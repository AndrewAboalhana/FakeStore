package com.aa.fakestore.ui.homeFragment

import androidx.annotation.StringRes
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.utils.State

data class ProductsCollection(
    @StringRes val titleId: Int,
    val product: State<List<AllProductsItem>>
)