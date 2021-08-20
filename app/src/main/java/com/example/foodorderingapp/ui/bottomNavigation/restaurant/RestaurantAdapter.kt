package com.example.foodorderingapp.ui.bottomNavigation.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.data.entity.RestaurantResponse
import com.example.foodorderingapp.data.entity.RestaurantResponseItem
import com.example.foodorderingapp.databinding.RestaurantItemBinding
import com.example.foodorderingapp.utils.IRestaurantListener

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    private lateinit var binding : RestaurantItemBinding
    private var listener : IRestaurantListener? = null
    private lateinit var restaurants : ArrayList<RestaurantResponseItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position],listener)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }
     class RestaurantViewHolder(private val binding : RestaurantItemBinding) : RecyclerView.ViewHolder(binding.root) {
         fun bind(restaurant: RestaurantResponseItem, listener: IRestaurantListener?){
             binding.restaurantName.text = restaurant.name
             binding.restaurantCategory.text = restaurant.category.categoryName
             binding.restaurantTime.text = restaurant.deliveryTime
             binding.restaurantMinOrder.text = "${restaurant.minDeliveryFee} TL"
             Glide.with(binding.root).load(restaurant.imageUrl).into(binding.restaurantPhoto)
             binding.restaurantContainer.setOnClickListener {
                 listener?.onClick(restaurant)
             }
         }
    }
    fun setRestaurants(restaurants : ArrayList<RestaurantResponseItem>){
        this.restaurants = restaurants
        notifyDataSetChanged()
    }
    fun setListener(listener: IRestaurantListener){
        this.listener = listener
    }
    fun removeListener(){
        listener = null
    }
}