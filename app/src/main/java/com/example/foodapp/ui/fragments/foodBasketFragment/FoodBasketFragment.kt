package com.example.foodapp.ui.fragments.foodBasketFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentFoodBasketBinding
import com.example.foodapp.enums.RepositoryStatus
import com.example.foodapp.ui.adapter.recyclerview.FoodListRecyclerAdapter
import com.example.foodapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodBasketFragment: BaseFragment<FragmentFoodBasketBinding>(FragmentFoodBasketBinding::inflate) {

    companion object {

        @JvmStatic
        fun newInstance() = FoodBasketFragment()
    }

    private lateinit var foodListAdapter: FoodListRecyclerAdapter
    private val foodBasketFragmentViewModel: FoodBasketFragmentViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_food_basket

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodListAdapter = FoodListRecyclerAdapter(arrayListOf()){

        }

        with(fragmentDataBinding.foodBasketListRecyclerView) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = foodListAdapter
        }
        foodBasketFragmentViewModel.getFoodsFromBasket(Constants.USER_ID)
        observeBasketFoodList()
    }

    private fun observeBasketFoodList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                foodBasketFragmentViewModel.foodList.collect {
                    when(it.status){
                        RepositoryStatus.LOADING -> {

                        }

                        RepositoryStatus.OK -> {
                            foodListAdapter.setFoodList(it.data!!.sepet_yemekler)
                        }

                        RepositoryStatus.ERROR -> {

                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }


}