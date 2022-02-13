package com.example.foodapp.data.remote.api

import com.example.foodapp.data.remote.reqres.DeleteFoodFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsFromBasketResponse
import com.example.foodapp.data.remote.reqres.FoodsResponse
import retrofit2.http.*

interface FoodApi {

    @GET("tumYemekleriGetir.php")
    suspend fun getAllFoods(): FoodsResponse

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addFoodToBasket(
        @Field("yemek_adi")
        food_name: String,
        @Field("yemek_resim_adi")
        food_photo_name: String,
        @Field("yemek_fiyat")
        food_cost: Int,
        @Field("yemek_siparis_adet")
        food_total: Int,
        @Field("kullanici_adi")
        user_id: String
    )

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getFoodsFromBasket(
        @Field("kullanici_adi")
        user_id: String
    ): FoodsFromBasketResponse

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFoodFromBasket(
        @Field("sepet_yemek_id")
        basket_food_id: Int,
        @Field("kullanici_adi")
        user_id: String
    ): DeleteFoodFromBasketResponse

    @GET("resimler/{foodName}")
    suspend fun getFoodsPhoto(
        @Path("foodName")
        food_name: String
    )
}