package com.aa.fakestore.ui.categoryFragment

import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.ui.base.BaseAdapter
import com.aa.fakestore.ui.base.BaseInteractionListener

class CategoryAdapter( items: List<AllProductsItem>, listener: CategoryInteractionListener) :
    BaseAdapter<AllProductsItem>(items, listener) {
    override val layoutId = R.layout.item_product_item

}

interface CategoryInteractionListener: BaseInteractionListener {
    fun goToDetails(productId: Int)
}