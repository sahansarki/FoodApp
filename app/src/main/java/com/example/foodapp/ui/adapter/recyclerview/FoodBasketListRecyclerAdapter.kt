package com.example.foodapp.ui.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.model.Food
import com.example.foodapp.model.FoodBasket
import com.example.foodapp.ui.adapter.viewholder.FoodBasketListViewHolder

class FoodBasketListRecyclerAdapter(
    private val foodList: ArrayList<FoodBasket>,
) : RecyclerView.Adapter<FoodBasketListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodBasketListViewHolder {
        return FoodBasketListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FoodBasketListViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount(): Int = foodList.size

    fun setFoodList(items: List<FoodBasket>) {
        foodList.clear()
        foodList.addAll(items)
        notifyDataSetChanged()
    }
}