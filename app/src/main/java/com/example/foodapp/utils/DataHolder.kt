package com.example.foodapp.utils

import com.example.foodapp.enums.RepositoryStatus

class DataHolder<out T> private constructor(val status: RepositoryStatus, val data: T?, val error: FoodError?) {

    companion object {

        fun <T> success(data: T): DataHolder<T> {
            return DataHolder(RepositoryStatus.OK, data, null)
        }

        fun <T> error(error: FoodError, data: T?): DataHolder<T> {
            return DataHolder(RepositoryStatus.ERROR, data, error)
        }

        fun <T> loading(): DataHolder<T> {
            return DataHolder(RepositoryStatus.LOADING, null, null)
        }
    }
}