package com.example.foodapp.model


import android.os.Parcelable
import com.example.foodapp.enums.FoodType
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    @SerializedName("yemek_id")
    val yemek_id : String,
    @SerializedName("yemek_adi")
    override val yemek_adi : String,
    @SerializedName("yemek_resim_adi")
    override val yemek_resim_adi : String,
    @SerializedName("yemek_fiyat")
    override val yemek_fiyat : String,
    override var itemViewType: Int
): Parcelable, BaseFoodModel()
