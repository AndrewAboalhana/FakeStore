package com.aa.fakestore.ui.homeScreen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aa.fakestore.R
import com.aa.fakestore.databinding.FragmentHomeBinding
import com.aa.fakestore.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment= R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun setUp() {
        val adapter = HomeAdapter(emptyList(),viewModel)
        binding.recyclerViewTodayProducts.adapter = adapter
        navigateToDetails()

        binding.textViewAll.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment()
            findNavController().navigate(directions)
        }

    }


    private fun navigateToDetails() {
        viewModel.navigationToDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

}