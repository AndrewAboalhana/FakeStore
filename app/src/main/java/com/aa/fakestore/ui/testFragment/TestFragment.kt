package com.aa.fakestore.ui.testFragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aa.fakestore.R
import com.aa.fakestore.databinding.FragmentTestBinding
import com.aa.fakestore.ui.base.BaseFragment
import com.aa.fakestore.ui.homeFragment.HomeFragmentDirections


class TestFragment : BaseFragment<FragmentTestBinding>() {
    override val layoutIdFragment= R.layout.fragment_test
    override val viewModel: TestViewModel by viewModels()

    override fun setUp() {
        val adapter = TodayProductsAdapter(emptyList(),viewModel)
        binding.recyclerViewTodayProducts.adapter = adapter
        val adapter2 = CategoryProductsAdapter(emptyList(),viewModel)
        binding.recyclerCategoryProducts.adapter = adapter2

        binding.chipsFilter.chipAllProducts.setOnClickListener {
            viewModel.fetchAllProducts()
        }
        binding.chipsFilter.chipElectronic.setOnClickListener {
            viewModel.fetchProductsInCategory(binding.chipsFilter.chipElectronic.text.toString())
        }
        binding.chipsFilter.chipJewelery.setOnClickListener {
            viewModel.fetchProductsInCategory(binding.chipsFilter.chipJewelery.text.toString())
        }
        binding.chipsFilter.chipMenClothes.setOnClickListener {
            viewModel.fetchProductsInCategory(binding.chipsFilter.chipMenClothes.text.toString())
        }
        binding.chipsFilter.chipWomenClothes.setOnClickListener {
            viewModel.fetchProductsInCategory(binding.chipsFilter.chipWomenClothes.text.toString())
        }
        navigateToDetails()
    }


    private fun navigateToDetails() {
        viewModel.navigationToDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    TestFragmentDirections.actionTestFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

}