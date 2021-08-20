package com.example.foodorderingapp.ui.bottomNavigation.restaurantDetail

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.databinding.MenuItemBinding
import com.example.foodorderingapp.utils.IFoodListener

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.FoodHolder>() {
    private lateinit var binding : MenuItemBinding
    private var foods = ArrayList<FoodItem>()
    private var listener : IFoodListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(food = foods[position],listener)
    }

    override fun getItemCount(): Int = foods.size

    class FoodHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food : FoodItem,listener: IFoodListener?){
            binding.foodName.text = food.name
            binding.foodPrice.text = food.price.toString()
            var ingredients : String =""
            food.ingredients.forEach {
                ingredients += "$it,"
            }
            binding.foodIngredients.text = ingredients
            Glide.with(binding.root).load(food.imageUrl).into(binding.foodImage)
            binding.foodContainer.setOnClickListener {
                listener?.onClick(food)
            }
        }
    }
    fun setFoods(foods : ArrayList<FoodItem>){
        this.foods = foods
    }
    fun setListener(listener: IFoodListener){
        this.listener = listener
    }
}