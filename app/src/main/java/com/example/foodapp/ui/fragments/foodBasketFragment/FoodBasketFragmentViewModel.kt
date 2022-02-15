package com.example.foodapp.ui.fragments.foodBasketFragment

import androidx.lifecycle.viewModelScope
import com.example.foodapp.base.BaseViewModel
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.repository.FoodRepository
import com.example.foodapp.utils.DataHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodBasketFragmentViewModel @Inject constructor(
    private val foodRepository: FoodRepository
): BaseViewModel() {

    private val mutableFoodList =  MutableStateFlow<DataHolder<FoodsFromBasketResponse>>(DataHolder.loading())
    val foodList: MutableStateFlow<DataHolder<FoodsFromBasketResponse>>
        get() = mutableFoodList

    fun getFoodsFromBasket(user_id: String){
       viewModelScope.launch(Dispatchers.Main) {
            val foodsFromBasketResponse = foodRepository.getFoodsFromBasket(user_id)
            mutableFoodList.value = foodsFromBasketResponse
        }
    }

    fun deleteFoodFromBasket(basket_food_id: Int, user_id: String){
        viewModelScope.launch {
            val deferred = async(Dispatchers.IO){
                foodRepository.deleteFoodFromBasket(basket_food_id,user_id)
            }
            deferred.await()
        }
    }



}