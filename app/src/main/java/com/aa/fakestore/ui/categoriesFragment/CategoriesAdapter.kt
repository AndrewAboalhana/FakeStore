package com.aa.fakestore.ui.categoriesFragment

import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllCategories
import com.aa.fakestore.ui.base.BaseAdapter
import com.aa.fakestore.ui.base.BaseInteractionListener

class CategoriesAdapter( items: List<AllCategories>,  listener: CategoriesInteractionListener) :
    BaseAdapter<AllCategories>(items, listener) {
    override val layoutId = R.layout.item_category
}

interface CategoriesInteractionListener : BaseInteractionListener {
    fun itemsInCategory(categoryName: String)
}