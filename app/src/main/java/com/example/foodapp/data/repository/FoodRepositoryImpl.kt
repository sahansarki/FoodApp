package com.example.foodapp.data.repository

import com.example.foodapp.data.remote.api.FoodApi
import com.example.foodapp.data.remote.reqres.DeleteFoodFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsResponse
import com.example.foodapp.repository.FoodRepository

class FoodRepositoryImpl(private val foodApi: FoodApi): FoodRepository {

    override suspend fun getAllFoods(): FoodsResponse {
        return foodApi.getAllFoods()
    }


    override suspend fun addFoodToBasket(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        return foodApi.addFoodToBasket(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }


    override suspend fun getFoodsFromBasket(kullanici_adi: String): FoodsFromBasketResponse {
        return foodApi.getFoodsFromBasket(kullanici_adi)
    }


    override suspend fun deleteFoodFromBasket(sepet_yemek_id: Int, kullanici_adi: String): DeleteFoodFromBasketResponse {
        return foodApi.deleteFoodFromBasket(sepet_yemek_id, kullanici_adi)
    }


    override suspend fun getFoodsPhoto(foodName: String) {
        return foodApi.getFoodsPhoto(foodName)
    }
}