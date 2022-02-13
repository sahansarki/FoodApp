package com.example.foodapp.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.FoodBasketItemRowBinding
import com.example.foodapp.model.Food
import com.example.foodapp.model.FoodBasket
import com.example.foodapp.ui.extensions.loadImage
import com.example.foodapp.utils.Constants

class FoodBasketListViewHolder(
    private val binding: FoodBasketItemRowBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(food: FoodBasket) {
        binding.food = food
        binding.foodPhoto.loadImage(Constants.FOOD_PHOTOS_URL + food.yemek_resim_adi)
        binding.foodSalary.text = (food.yemek_fiyat.toInt() * food.yemek_siparis_adet.toInt()).toString()

    }

    companion object {
        fun create(
            parent: ViewGroup
        ): FoodBasketListViewHolder {
            val view =
                FoodBasketItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FoodBasketListViewHolder(view)
        }
    }
}