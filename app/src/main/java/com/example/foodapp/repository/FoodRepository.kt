package com.example.foodapp.repository

import com.example.foodapp.data.remote.reqres.DeleteFoodFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsResponse

interface FoodRepository {
    suspend fun getAllFoods(): FoodsResponse
    suspend fun addFoodToBasket(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    )
    suspend fun getFoodsFromBasket(kullanici_adi: String): FoodsFromBasketResponse
    suspend fun deleteFoodFromBasket(sepet_yemek_id: Int, kullanici_adi: String): DeleteFoodFromBasketResponse
    suspend fun getFoodsPhoto(foodName: String)
}