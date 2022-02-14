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
    mContext: Context,
    binding: FoodBasketItemRowBinding
): RecyclerView.ViewHolder(binding.root) {

    val bindingLocal = binding
    val contextLocal = mContext

    inline fun bind(food: FoodBasket, crossinline deleteFoodItem: (food: FoodBasket) -> Unit) {
        bindingLocal.food = food
        bindingLocal.foodPhoto.loadImage(Constants.FOOD_PHOTOS_URL + food.yemek_resim_adi)
        bindingLocal.foodSalary.text = (food.yemek_fiyat.toInt() * food.yemek_siparis_adet.toInt()).toString()
        bindingLocal.foodCardMore.setOnClickListener {
            val popup = PopupMenu(contextLocal,it)
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