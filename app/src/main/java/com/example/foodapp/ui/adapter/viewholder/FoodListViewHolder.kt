package com.example.foodapp.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.FoodItemRowBinding
import com.example.foodapp.model.Food
import com.example.foodapp.ui.extensions.loadImage
import com.example.foodapp.utils.Constants.FOOD_PHOTOS_URL

class FoodListViewHolder(
        private val binding: FoodItemRowBinding,
): RecyclerView.ViewHolder(binding.root) {

    fun bind(food: Food) {
        binding.food = food
        binding.foodPhoto.loadImage(FOOD_PHOTOS_URL + food.yemek_resim_adi)

    }

    companion object{
        fun create(
            parent: ViewGroup
        ): FoodListViewHolder{
            val view = FoodItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FoodListViewHolder(view)
        }
    }
}