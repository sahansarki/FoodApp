package com.example.foodapp.di

import com.example.foodapp.data.remote.RetrofitClient
import com.example.foodapp.data.remote.api.FoodApi
import com.example.foodapp.data.repository.FoodRepositoryImpl
import com.example.foodapp.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodApi(): FoodApi{
        return RetrofitClient.getInstance().create(FoodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(foodApi: FoodApi): FoodRepository{
        return FoodRepositoryImpl(foodApi)
    }


}