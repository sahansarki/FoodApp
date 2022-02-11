package com.example.foodapp.ui.fragments.foodListFragment

import androidx.lifecycle.viewModelScope
import com.example.foodapp.base.BaseViewModel
import com.example.foodapp.data.remote.reqres.FoodsResponse
import com.example.foodapp.data.repository.FoodRepositoryImpl
import com.example.foodapp.repository.FoodRepository
import com.example.foodapp.utils.DataHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListFragmentViewModel @Inject constructor(
    private val foodRepositoryImpl: FoodRepository
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