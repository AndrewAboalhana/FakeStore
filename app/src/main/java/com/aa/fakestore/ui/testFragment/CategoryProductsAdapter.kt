package com.aa.fakestore.ui.testFragment

import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.ui.base.BaseAdapter

class CategoryProductsAdapter(items:List<AllProductsItem>,listener: ProductInteractionListener)
    : BaseAdapter<AllProductsItem>(items,listener) {
    override val layoutId = R.layout.item_product_item
}