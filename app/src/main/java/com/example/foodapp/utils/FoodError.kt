package com.example.foodapp.utils

class FoodError(
    override val message: String,
    override val cause: Throwable? = null
): Throwable(null, null)