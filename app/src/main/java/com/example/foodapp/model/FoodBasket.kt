package com.example.foodapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodBasket(
    @SerializedName("sepet_yemek_id")
    val sepet_yemek_id: String,
    @SerializedName("yemek_adi")
    val yemek_adi: String,
    @SerializedName("yemek_resim_adi")
    val yemek_resim_adi: String,
    @SerializedName("yemek_fiyat")
    val yemek_fiyat: String,
    @SerializedName("yemek_siparis_adet")
    val yemek_siparis_adet: String,
    @SerializedName("kullanici_adi")
    val kullanici_adi: String
): Parcelable
