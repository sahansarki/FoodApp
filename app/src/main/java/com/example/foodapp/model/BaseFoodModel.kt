package com.example.foodapp.model

abstract class BaseFoodModel {
    abstract val yemek_adi : String
    abstract val yemek_resim_adi : String
    abstract val yemek_fiyat : String
    abstract var itemViewType: Int
}