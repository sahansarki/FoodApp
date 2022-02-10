package com.example.foodapp.ui.fragments.foodListFragment

import android.os.Bundle
import android.view.View
import com.example.foodapp.R
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentFoodListBinding


class FoodListFragment: BaseFragment<FragmentFoodListBinding>(FragmentFoodListBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = FoodListFragment()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_food_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
}