package com.example.foodapp.ui.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.enums.FoodType
import com.example.foodapp.model.BaseFoodModel
import com.example.foodapp.model.Food
import com.example.foodapp.model.FoodBasket
import com.example.foodapp.ui.adapter.viewholder.FoodBasketListViewHolder
import com.example.foodapp.ui.adapter.viewholder.FoodListViewHolder

class FoodListRecyclerAdapter(
    private val orderFood: (food: Food) -> Unit,
    private val deleteFoodItem: (food: FoodBasket) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            FoodType.FOOD.id -> {
               FoodListViewHolder.create(parent)
            }

            FoodType.FOOD_BASKET.id -> {
                FoodBasketListViewHolder.create(parent)
            }
            else -> {
                FoodListViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(differ.currentList[position].itemViewType){
            FoodType.FOOD.id -> {
                (holder as FoodListViewHolder).bind(differ.currentList[position] as Food,orderFood)
            }
            FoodType.FOOD_BASKET.id -> {
                (holder as FoodBasketListViewHolder).bind(differ.currentList[position] as FoodBasket, deleteFoodItem)
            }
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return differ.currentList[position].itemViewType
    }

    fun setFoodList(items: List<BaseFoodModel>) {
        when {
            items.isEmpty() -> {
                differ.submitList(items)
                return
            }
            items[0] is FoodBasket -> {
                items.forEach {
                    it.itemViewType = FoodType.FOOD_BASKET.id
                }
            }
            else -> {
                items.forEach{
                    it.itemViewType = FoodType.FOOD.id
                }
            }
        }
        differ.submitList(items)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BaseFoodModel>() {
            override fun areItemsTheSame(oldItem: BaseFoodModel, newItem: BaseFoodModel) = oldItem == newItem
            override fun areContentsTheSame(oldItem: BaseFoodModel, newItem: BaseFoodModel) =
                oldItem.yemek_adi == newItem.yemek_adi
        }
    }
}