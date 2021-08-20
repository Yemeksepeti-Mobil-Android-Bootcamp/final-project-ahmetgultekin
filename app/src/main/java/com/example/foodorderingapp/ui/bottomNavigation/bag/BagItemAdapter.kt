package com.example.foodorderingapp.ui.bottomNavigation.bag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.databinding.BagItemBinding
import com.example.foodorderingapp.utils.IBagItemRemoveListener

class BagItemAdapter() : RecyclerView.Adapter<BagItemAdapter.BagItemViewHolder>() {
    private lateinit var binding : BagItemBinding
    private var bagItems = ArrayList<BagItem>()
    private lateinit var listener:IBagItemRemoveListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagItemViewHolder {
        binding = BagItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BagItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BagItemViewHolder, position: Int) {
        holder.bind(bagItems[position],listener)
    }

    override fun getItemCount(): Int = bagItems.size

    class BagItemViewHolder(private val binding: BagItemBinding,) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bagItem: BagItem,listener: IBagItemRemoveListener) {
            binding.bagItemName.text = bagItem.foodItem.name
            binding.bagItemRestaurant.text = bagItem.restaurantName
            Glide.with(binding.root).load(bagItem.foodItem.imageUrl).into(binding.bagItemImage)
            binding.bagItemPrice.text = bagItem.foodItem.price.toString()
            binding.bagItemRemove.setOnClickListener {
                listener.onClick(bagItem)
            }
        }

    }
    fun setBagItem(bagItems : ArrayList<BagItem>){
        this.bagItems = bagItems
    }
    fun setListener(listener: IBagItemRemoveListener){
        this.listener = listener
    }
}