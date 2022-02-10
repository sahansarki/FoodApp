package com.example.foodapp.ui.fragments.foodListFragment

import androidx.lifecycle.viewModelScope
import com.example.foodapp.base.BaseViewModel
import com.example.foodapp.data.remote.reqres.FoodsResponse
import com.example.foodapp.data.repository.FoodRepositoryImpl
import com.example.foodapp.utils.DataHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FoodListFragmentViewModel(
    private val foodRepositoryImpl: FoodRepositoryImpl
): BaseViewModel() {

    private val mutableFoodList =  MutableStateFlow<DataHolder<FoodsResponse>>(DataHolder.loading())
    val foodList: MutableStateFlow<DataHolder<FoodsResponse>>
        get() = mutableFoodList

    fun getAllFoods(){
        viewModelScope.launch(Dispatchers.Main) {
            mutableFoodList.value = DataHolder.success(foodRepositoryImpl.getAllFoods())
        }
    }


}