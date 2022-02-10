package com.example.foodapp.data.repository

import com.example.foodapp.data.remote.api.FoodApi
import com.example.foodapp.data.remote.reqres.DeleteFoodFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsResponse

class FoodRepositoryImpl(private val foodApi: FoodApi) {

    suspend fun getAllFoods(): FoodsResponse {
        return foodApi.getAllFoods()
    }


    suspend fun addFoodToBasket(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        return foodApi.addFoodToBasket(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }


    suspend fun getFoodsFromBasket(kullanici_adi: String): FoodsFromBasketResponse {
        return foodApi.getFoodsFromBasket(kullanici_adi)
    }


    suspend fun deleteFoodFromBasket(sepet_yemek_id: Int, kullanici_adi: String): DeleteFoodFromBasketResponse {
        return foodApi.deleteFoodFromBasket(sepet_yemek_id, kullanici_adi)
    }


    suspend fun getFoodsPhoto(foodName: String) {
        return foodApi.getFoodsPhoto(foodName)
    }
}