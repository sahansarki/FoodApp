package com.example.foodapp.ui.fragments.foodBottomSheetFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.foodapp.R
import com.example.foodapp.databinding.BottomSheetBinding
import com.example.foodapp.model.Food
import com.example.foodapp.ui.extensions.loadImage
import com.example.foodapp.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FoodBottomSheetFragment(private val food: Food): BottomSheetDialogFragment(){

    private lateinit var bottomSheetBinding: BottomSheetBinding

    companion object{
        const val TAG = "ActionBottomDialog"
        fun newInstance(food: Food):FoodBottomSheetFragment{
            return FoodBottomSheetFragment(food)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)
        return bottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBinding.food = food
        bottomSheetBinding.foodPhoto.loadImage(Constants.FOOD_PHOTOS_URL + food.yemek_resim_adi)
    }

}