package com.example.foodapp.ui.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.enums.FoodType
import com.example.foodapp.model.BaseFoodModel
import com.example.foodapp.model.Food
import com.example.foodapp.model.FoodBasket
import com.example.foodapp.ui.adapter.viewholder.FoodBasketListViewHolder
import com.example.foodapp.ui.adapter.viewholder.FoodListViewHolder

class FoodListRecyclerAdapter(
    private val foodList: ArrayList<BaseFoodModel>,
    private val orderFood: (food: Food) -> Unit,
    private val deleteFoodItem: (food: FoodBasket) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

        when(foodList[position].itemViewType){
            FoodType.FOOD.id -> {
                (holder as FoodListViewHolder).bind(foodList[position] as Food,orderFood)
            }
            FoodType.FOOD_BASKET.id -> {
                (holder as FoodBasketListViewHolder).bind(foodList[position] as FoodBasket, deleteFoodItem)
            }
        }

    }

    override fun getItemCount(): Int = foodList.size

    override fun getItemViewType(position: Int): Int {
        return foodList[position].itemViewType
    }

    fun setFoodList(items: List<BaseFoodModel>) {
        when {
            items.isEmpty() -> {
                foodList.clear()
                notifyDataSetChanged()
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
        foodList.clear()
        foodList.addAll(items)
        notifyDataSetChanged()
    }
}