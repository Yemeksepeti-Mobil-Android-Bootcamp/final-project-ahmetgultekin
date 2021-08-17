package com.example.foodorderingapp.ui.bottomNavigation.restaurantDetail

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.databinding.MenuItemBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.FoodHolder>() {
    private lateinit var binding : MenuItemBinding
    private var foods = ArrayList<FoodItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(food = foods[position])
    }

    override fun getItemCount(): Int = foods.size

    class FoodHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food : FoodItem){
            binding.foodName.text = food.name
            binding.foodPrice.text = food.price
            Glide.with(binding.root).load(food.imageUrl).into(binding.foodImage)
        }
    }
    fun setFoods(foods : ArrayList<FoodItem>){
        this.foods = foods
    }
}