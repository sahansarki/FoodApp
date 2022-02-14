package com.example.foodapp.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.FoodItemRowBinding
import com.example.foodapp.model.Food
import com.example.foodapp.ui.extensions.loadImage
import com.example.foodapp.utils.Constants.FOOD_PHOTOS_URL

class FoodListViewHolder(
    binding: FoodItemRowBinding,
) : RecyclerView.ViewHolder(binding.root) {

    val localBinding = binding

    inline fun bind(food: Food, crossinline orderFood: (food: Food) -> Unit) {
        localBinding.food = food
        localBinding.foodPhoto.loadImage(FOOD_PHOTOS_URL + food.yemek_resim_adi)
        localBinding.foodCard.setOnClickListener {
            orderFood(food)
        }

    }

    companion object {
        fun create(
            parent: ViewGroup
        ): FoodListViewHolder {
            val view =
                FoodItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FoodListViewHolder(view)
        }
    }
}