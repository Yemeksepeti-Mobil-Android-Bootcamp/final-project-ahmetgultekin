package com.example.foodorderingapp.di

import android.content.Context
import com.example.foodorderingapp.data.local.PaperManager
import com.example.foodorderingapp.data.local.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context) : SharedPreferencesManager{
        return SharedPreferencesManager(context)
    }

    @Provides
    fun providesPaperManager(@ApplicationContext context: Context) : PaperManager{
        return PaperManager(context)
    }
}