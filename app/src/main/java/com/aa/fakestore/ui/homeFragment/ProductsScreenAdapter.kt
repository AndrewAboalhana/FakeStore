package com.aa.fakestore.ui.homeFragment

import com.aa.fakestore.R
import com.aa.fakestore.data.model.AllProductsItem
import com.aa.fakestore.databinding.ItemCategoryListBinding
import com.aa.fakestore.ui.base.BaseAdapter
import com.aa.fakestore.ui.base.BaseInteractionListener

class ProductsScreenAdapter(
    items: List<ProductsCollection>,
    private val listener: ProductListenerInteraction
): BaseAdapter<ProductsCollection>(items,listener) {
    override val layoutId: Int = R.layout.item_category_list

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when(holder){
            is ItemViewHolder -> {
                (holder.binding as ItemCategoryListBinding).recyclerComics.adapter = ProductAdapter(
                    emptyList(),
                    listener
                )
            }
        }
    }
}

interface ProductListenerInteraction : BaseInteractionListener {
    fun onClick(item: AllProductsItem)

    fun onTryAgainClicked()
}