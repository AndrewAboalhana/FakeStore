package com.aa.fakestore.ui.detailsFragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aa.fakestore.R
import com.aa.fakestore.databinding.FragmentDetailsBinding
import com.aa.fakestore.ui.base.BaseFragment


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_details

    override val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun setUp() {
        val productId = args.productId
        viewModel.fetchData(productId)
        binding.buttonBuy.setOnClickListener {
            Toast.makeText(context,"Added to your cart",Toast.LENGTH_SHORT).show()
        }
    }

}