package com.example.foodapp.data.remote.reqres

import com.example.foodapp.model.FoodBasket
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsFromBasketResponse(
    @SerializedName("sepet_yemekler")
    @Expose
    val sepet_yemekler: List<FoodBasket>,
    @SerializedName("success")
    @Expose
    val success: String,
)