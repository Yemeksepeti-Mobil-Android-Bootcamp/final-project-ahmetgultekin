package com.example.foodorderingapp.di

import androidx.viewbinding.BuildConfig
import com.example.foodorderingapp.data.remote.RestaurantApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesRestaurantService(retrofit: Retrofit):RestaurantApiService{
        return retrofit.create(RestaurantApiService::class.java)
    }

    @Provides
    fun providesRetrofit(baseUrl: String,okHttpClient: OkHttpClient,gson: Gson) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    fun provideGson():Gson = Gson()

    @Provides
    fun providesBaseUrl():String = "https://yemeksepeti-ag.herokuapp.com/api/"

}