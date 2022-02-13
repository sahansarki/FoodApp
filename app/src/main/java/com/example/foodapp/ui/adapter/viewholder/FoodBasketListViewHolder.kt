package com.example.foodapp.ui.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.FoodBasketItemRowBinding
import com.example.foodapp.model.Food
import com.example.foodapp.model.FoodBasket
import com.example.foodapp.ui.extensions.loadImage
import com.example.foodapp.utils.Constants

class FoodBasketListViewHolder(
    private val mContext: Context,
    private val binding: FoodBasketItemRowBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(food: FoodBasket, deleteFoodItem: (food: FoodBasket) -> Unit) {
        binding.food = food
        binding.foodPhoto.loadImage(Constants.FOOD_PHOTOS_URL + food.yemek_resim_adi)
        binding.foodSalary.text = (food.yemek_fiyat.toInt() * food.yemek_siparis_adet.toInt()).toString()
        binding.foodCardMore.setOnClickListener {
            val popup = PopupMenu(mContext,it)
            popup.menuInflater.inflate(R.menu.basket_item_menu, popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener { item ->
                when(item?.itemId){
                    R.id.action_delete -> {
                        deleteFoodItem(food)
                        true
                    }

                    else -> false
                }
            }
        }

    }

    companion object {
        fun create(
            parent: ViewGroup
        ): FoodBasketListViewHolder {
            val view =
                FoodBasketItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FoodBasketListViewHolder(parent.context,view)
        }
    }
}