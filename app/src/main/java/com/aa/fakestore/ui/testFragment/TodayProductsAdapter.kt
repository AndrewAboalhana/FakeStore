package com.aa.fakestore.ui.testFragment

import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.ui.base.BaseAdapter
import com.aa.fakestore.ui.base.BaseInteractionListener

class TodayProductsAdapter(item: List<AllProductsItem>, listener: ProductInteractionListener) :
    BaseAdapter<AllProductsItem>(item, listener) {
    override val layoutId = R.layout.item_home_card
}


interface ProductInteractionListener : BaseInteractionListener {
    fun onClick(productId:Int)
}