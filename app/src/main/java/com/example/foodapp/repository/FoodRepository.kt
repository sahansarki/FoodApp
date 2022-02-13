package com.example.foodapp.repository

import com.example.foodapp.data.remote.reqres.DeleteFoodFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsResponse
import com.example.foodapp.utils.DataHolder

interface FoodRepository {
    suspend fun getAllFoods(): DataHolder<FoodsResponse>
    suspend fun addFoodToBasket(
        food_name: String,
        food_photo_name: String,
        food_cost: Int,
        food_total: Int,
        user_id: String
    )
    suspend fun getFoodsFromBasket(user_id: String): DataHolder<FoodsFromBasketResponse>
    suspend fun deleteFoodFromBasket(basket_food_id: Int, user_id: String): DeleteFoodFromBasketResponse
    suspend fun getFoodsPhoto(food_name: String)
}