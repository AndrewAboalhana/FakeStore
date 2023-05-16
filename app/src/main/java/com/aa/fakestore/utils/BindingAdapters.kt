package com.aa.fakestore.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aa.fakestore.R
import com.aa.fakestore.ui.base.BaseAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?){
    if (state is State.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?){
    if (state is State.Failed){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?){
    if (state is State.Success){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view).load(url).into(view)
}

@BindingAdapter(value = ["app:setItems"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        (view.adapter as BaseAdapter<T>).setItems(items)
    } else {
        (view.adapter as BaseAdapter<T>).setItems(emptyList())
    }
}

@BindingAdapter("categoryImage")
fun setCategoryImage(imageView: ImageView, category: String?) {
    category?.let {
        val drawableId = when (it) {
            "electronics" -> R.drawable.electronics
            "jewelery" -> R.drawable.jewelery
            "men's clothing" -> R.drawable.men_clothes
            else -> R.drawable.women_clothes
        }
        imageView.setImageResource(drawableId)
    }
}