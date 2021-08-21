package com.example.foodorderingapp.ui.bottomNavigation.account.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.data.entity.Order
import com.example.foodorderingapp.data.entity.OrderResponseItem
import com.example.foodorderingapp.databinding.FragmentOrdersBinding
import com.example.foodorderingapp.databinding.OrdersItemBinding

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    private  var orders = ArrayList<OrderResponseItem>()

    private lateinit var binding : OrdersItemBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrdersAdapter.OrderViewHolder {
        binding = OrdersItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersAdapter.OrderViewHolder, position: Int) {
       holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size


    class OrderViewHolder(private val binding : OrdersItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: OrderResponseItem) {
            binding.ordersName.text = order.meal.name
            binding.ordersRestaurantName.text = order.restaurant
            binding.ordersPrice.text = "${order.price} TL"
            binding.ordersQuantity.text = "Quantity: ${order.quantity}"
            binding.ordersPaymentMethod.text = order.paymentType[0]
            Glide.with(binding.root).load(order.meal.imageUrl).into(binding.imageView2)
        }

    }
    fun setOrder(orders : ArrayList<OrderResponseItem>){
        this.orders = orders
        notifyDataSetChanged()
    }

}