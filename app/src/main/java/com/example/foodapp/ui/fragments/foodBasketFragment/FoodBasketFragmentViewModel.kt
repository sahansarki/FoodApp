package com.example.foodapp.ui.fragments.foodBasketFragment

import com.example.foodapp.base.BaseViewModel
import com.example.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodBasketFragmentViewModel @Inject constructor(
    private val foodRepository: FoodRepository
): BaseViewModel() {



}