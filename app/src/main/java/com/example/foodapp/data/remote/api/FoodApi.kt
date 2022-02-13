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
        yemek_adi: String,
        @Field("yemek_resim_adi")
        yemek_resim_adi: String,
        @Field("yemek_fiyat")
        yemek_fiyat: Int,
        @Field("yemek_siparis_adet")
        yemek_siparis_adet: Int,
        @Field("kullanici_adi")
        kullanici_adi: String
    )

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getFoodsFromBasket(
        @Field("kullanici_adi")
        kullanici_adi: String
    ): FoodsFromBasketResponse

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFoodFromBasket(
        @Field("sepet_yemek_id")
        sepet_yemek_id: Int,
        @Field("kullanici_adi")
        kullanici_adi: String
    ): DeleteFoodFromBasketResponse

    @GET("resimler/{foodName}")
    suspend fun getFoodsPhoto(
        @Path("foodName")
        foodName: String
    )
}