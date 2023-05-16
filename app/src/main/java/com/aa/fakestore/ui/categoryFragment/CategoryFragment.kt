package com.aa.fakestore.ui.categoryFragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aa.fakestore.R
import com.aa.fakestore.databinding.FragmentCategoryBinding
import com.aa.fakestore.ui.base.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(){
    override val layoutIdFragment = R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()

    override fun setUp() {
        val categoryName = args.categoryName
        viewModel.fetchProductsInCategory(categoryName)
        val adapter = CategoryAdapter(emptyList(),viewModel)
        binding.recyclerViewCategory.adapter = adapter
        navigateToDetails()
    }

    private fun navigateToDetails() {
        viewModel.navigationToDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }


}