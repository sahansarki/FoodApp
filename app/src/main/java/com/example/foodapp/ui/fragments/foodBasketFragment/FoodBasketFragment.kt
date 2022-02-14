package com.example.foodapp.ui.fragments.foodBasketFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.example.foodapp.ui.extensions.dismissProgress
import com.example.foodapp.ui.extensions.showProgress
import com.example.foodapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodBasketFragment: BaseFragment<FragmentFoodBasketBinding>(FragmentFoodBasketBinding::inflate) {

    private lateinit var foodListAdapter: FoodListRecyclerAdapter
    private val foodBasketFragmentViewModel: FoodBasketFragmentViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_food_basket

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodListAdapter = FoodListRecyclerAdapter({

        }) {
            foodBasketFragmentViewModel.deleteFoodFromBasket(it.sepet_yemek_id.toInt(), it.kullanici_adi)
            foodBasketFragmentViewModel.getFoodsFromBasket(Constants.USER_ID)
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
                            requireContext().showProgress()
                        }
                        RepositoryStatus.OK -> {
                            requireContext().dismissProgress()
                            foodListAdapter.setFoodList(it.data!!.sepet_yemekler)
                        }

                        RepositoryStatus.ERROR -> {
                            requireContext().dismissProgress()
                            Toast.makeText(requireContext(), it.error!!.message, Toast.LENGTH_SHORT).show()
                            foodListAdapter.setFoodList(listOf())
                        }
                    }
                }
            }
        }
    }

}