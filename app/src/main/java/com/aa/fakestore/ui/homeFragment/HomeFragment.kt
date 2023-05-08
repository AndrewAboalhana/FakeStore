package com.aa.fakestore.ui.homeFragment

import androidx.fragment.app.viewModels
import com.aa.fakestore.R
import com.aa.fakestore.databinding.FragmentHomeBinding
import com.aa.fakestore.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    override fun setUp() {
        val productsAdapter = ProductsScreenAdapter(emptyList(),viewModel)
        binding.recyclerViewHome.adapter = productsAdapter
    }

}