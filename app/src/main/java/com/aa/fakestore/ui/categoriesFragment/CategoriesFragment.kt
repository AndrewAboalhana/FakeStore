package com.aa.fakestore.ui.categoriesFragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aa.fakestore.R
import com.aa.fakestore.databinding.FragmentCategoriesBinding
import com.aa.fakestore.ui.base.BaseFragment


class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override val layoutIdFragment = R.layout.fragment_categories
    override val viewModel: CategoriesViewModel by viewModels()

    override fun setUp() {
        val adapter = CategoriesAdapter(emptyList(),viewModel)
        binding.recyclerViewCategories.adapter = adapter
        navigateToDetails()
    }

    private fun navigateToDetails() {
        viewModel.navigationToDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    CategoriesFragmentDirections.actionCategoriesFragmentToCategoryFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

}