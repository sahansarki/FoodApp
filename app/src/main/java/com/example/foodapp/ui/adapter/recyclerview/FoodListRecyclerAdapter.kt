package com.example.foodapp.ui.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.model.Food
import com.example.foodapp.ui.adapter.viewholder.FoodListViewHolder

class FoodListRecyclerAdapter(
    private val foodList: ArrayList<Food>,
    private val orderFood: (food: Food) -> Unit
): RecyclerView.Adapter<FoodListViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        return FoodListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        holder.bind(foodList[position],orderFood)
    }

    override fun getItemCount(): Int = foodList.size

    fun setFoodList(items: List<Food>){
        foodList.clear()
        foodList.addAll(items)
        notifyDataSetChanged()
    }
}