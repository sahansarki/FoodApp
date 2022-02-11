package com.example.foodapp.ui.fragments.foodBasketFragment

import android.os.Bundle
import android.view.View
import com.example.foodapp.R
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentFoodBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodBasketFragment: BaseFragment<FragmentFoodBasketBinding>(FragmentFoodBasketBinding::inflate) {

    companion object {

        @JvmStatic
        fun newInstance() = FoodBasketFragment()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_food_basket

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


}