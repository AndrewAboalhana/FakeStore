package com.aa.fakestore.ui.homeFragment


import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.ui.base.BaseAdapter

class ProductAdapter(
    items: List<AllProductsItem>,
    listener: ProductListenerInteraction,
) : BaseAdapter<AllProductsItem>(items, listener) {
    override val layoutId: Int = R.layout.item_home_card

}

