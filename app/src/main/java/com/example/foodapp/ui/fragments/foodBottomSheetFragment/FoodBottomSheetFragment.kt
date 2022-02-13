package com.example.foodapp.ui.fragments.foodBottomSheetFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.foodapp.R
import com.example.foodapp.databinding.BottomSheetBinding
import com.example.foodapp.enums.RepositoryStatus
import com.example.foodapp.model.Food
import com.example.foodapp.ui.extensions.loadImage
import com.example.foodapp.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodBottomSheetFragment(private val food: Food) : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBinding: BottomSheetBinding
    private lateinit var minusIcon: ImageButton
    private lateinit var plusIcon: ImageButton
    private lateinit var addToBasket: Button
    private lateinit var foodTotal: TextView
    private val foodBottomSheetFragmentViewModel: FoodBottomSheetFragmentViewModel by viewModels()

    private var resultTotal = 0


    companion object {
        const val TAG = "ActionBottomDialog"
        fun newInstance(food: Food): FoodBottomSheetFragment {
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
        minusIcon = bottomSheetBinding.minusIcon
        plusIcon = bottomSheetBinding.plusIcon
        addToBasket = bottomSheetBinding.addToBasket
        foodTotal = bottomSheetBinding.foodTotal
        return bottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBinding.food = food
        bottomSheetBinding.foodPhoto.loadImage(Constants.FOOD_PHOTOS_URL + food.yemek_resim_adi)
        minusIcon.setOnClickListener {
            if(foodTotal.text.toString().toInt() <= 0) return@setOnClickListener
            val newTotal = foodTotal.text.toString().toInt() - 1
            bottomSheetBinding.foodTotal.text = newTotal.toString()
        }

        plusIcon.setOnClickListener {
            val newTotal = foodTotal.text.toString().toInt() + 1
            bottomSheetBinding.foodTotal.text = newTotal.toString()
        }

        addToBasket.setOnClickListener {
            if(foodTotal.text.toString().toInt() <= 0) return@setOnClickListener
            resultTotal = foodTotal.text.toString().toInt()
            foodBottomSheetFragmentViewModel.getFoodsFromBasket(Constants.USER_ID)
            Toast.makeText(requireContext(),"Siparişiniz sepete eklendi!",Toast.LENGTH_SHORT).show()
        }

        observeFoodList()
    }

    private fun observeFoodList() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                foodBottomSheetFragmentViewModel.foodList.collect {
                    when(it.status){
                        RepositoryStatus.LOADING -> {

                        }

                        RepositoryStatus.OK -> {
                            for (i in it.data!!.sepet_yemekler){
                                if(i.yemek_adi == food.yemek_adi) {
                                    resultTotal += i.yemek_siparis_adet.toInt()
                                    foodBottomSheetFragmentViewModel.deleteFoodFromBasket(i.sepet_yemek_id.toInt(), i.kullanici_adi)
                                    foodBottomSheetFragmentViewModel.addFoodToBasket(food, resultTotal)
                                    return@collect
                                }

                            }
                            foodBottomSheetFragmentViewModel.addFoodToBasket(food, resultTotal)

                        }

                        RepositoryStatus.ERROR -> {
                            Toast.makeText(requireContext(), it.error!!.message, Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }

}