package com.example.foodapp.data.repository

import com.example.foodapp.data.remote.api.FoodApi
import com.example.foodapp.data.remote.reqres.DeleteFoodFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsResponse
import com.example.foodapp.repository.FoodRepository
import com.example.foodapp.utils.DataHolder
import com.example.foodapp.utils.FoodError

class FoodRepositoryImpl(private val foodApi: FoodApi): FoodRepository {

    override suspend fun getAllFoods(): DataHolder<FoodsResponse> {
        val foodsResponse: DataHolder<FoodsResponse> = try{
            DataHolder.success(foodApi.getAllFoods())
        } catch (e: Exception){
            DataHolder.error(FoodError(e.localizedMessage.toString()),null)
        }
        return foodsResponse
    }


    override suspend fun addFoodToBasket(
        food_name: String,
        food_photo_name: String,
        food_cost: Int,
        food_total: Int,
        user_id: String
    ) {
        return foodApi.addFoodToBasket(food_name,food_photo_name,food_cost,food_total,user_id)
    }


    override suspend fun getFoodsFromBasket(user_id: String): DataHolder<FoodsFromBasketResponse> {
        val foodsFromBasketResponse: DataHolder<FoodsFromBasketResponse> = try{
            DataHolder.success(foodApi.getFoodsFromBasket(user_id))
        } catch (e: Exception){
            DataHolder.error(FoodError("Basket list is empty"),null)
        }
        return foodsFromBasketResponse
    }


    override suspend fun deleteFoodFromBasket(basket_food_id: Int, user_id: String): DeleteFoodFromBasketResponse {
        return foodApi.deleteFoodFromBasket(basket_food_id, user_id)
    }


    override suspend fun getFoodsPhoto(food_name: String) {
        return foodApi.getFoodsPhoto(food_name)
    }
}