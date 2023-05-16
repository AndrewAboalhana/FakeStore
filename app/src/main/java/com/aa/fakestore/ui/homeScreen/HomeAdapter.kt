package com.aa.fakestore.ui.homeScreen

import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.ui.base.BaseAdapter
import com.aa.fakestore.ui.categoryFragment.CategoryInteractionListener

class HomeAdapter(items:List<AllProductsItem>, listener: CategoryInteractionListener)
    : BaseAdapter<AllProductsItem>(items,listener) {
    override val layoutId = R.layout.item_home_card
}