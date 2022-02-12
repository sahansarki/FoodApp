package com.example.foodapp.ui.fragments.foodListFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentFoodListBinding
import com.example.foodapp.enums.RepositoryStatus
import com.example.foodapp.model.Food
import com.example.foodapp.ui.adapter.recyclerview.FoodListRecyclerAdapter
import com.example.foodapp.ui.fragments.foodBottomSheetFragment.FoodBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodListFragment: BaseFragment<FragmentFoodListBinding>(FragmentFoodListBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = FoodListFragment()
    }

    private val foodListFragmentViewModel: FoodListFragmentViewModel by viewModels()
    private lateinit var foodListAdapter: FoodListRecyclerAdapter

    override fun getLayoutResId(): Int = R.layout.fragment_food_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodListAdapter = FoodListRecyclerAdapter(arrayListOf()){
            openBottomSheet(it)
        }

        with(fragmentDataBinding.foodListRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = foodListAdapter
        }
        foodListFragmentViewModel.getAllFoods()
        observeFoodList()
    }

    private fun openBottomSheet(food: Food) {
        val bottomSheet = FoodBottomSheetFragment.newInstance(food)
        bottomSheet.show(
            parentFragmentManager, FoodBottomSheetFragment.TAG
        )

    }

    override fun onResume() {
        super.onResume()
    }

    private fun observeFoodList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                foodListFragmentViewModel.foodList.collect {
                    when(it.status){
                        RepositoryStatus.LOADING -> {

                        }

                        RepositoryStatus.OK -> {
                            foodListAdapter.setFoodList(it.data!!.yemekler)
                        }

                        RepositoryStatus.ERROR -> {

                        }
                    }
                }
            }
        }
    }
}