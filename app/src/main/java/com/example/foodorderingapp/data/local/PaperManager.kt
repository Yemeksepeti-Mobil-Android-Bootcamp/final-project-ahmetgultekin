package com.example.foodorderingapp.data.local

import android.content.Context
import android.util.Log
import com.example.foodorderingapp.data.entity.FoodItem
import io.paperdb.Paper

class PaperManager(context : Context) {
    private val items = ArrayList<FoodItem>()
    companion object{
        const val BAG_ITEM = "com.example.foodorderingapp.bag_item"
    }
    init {
        Paper.init(context)
    }

    fun addToBag(foodItem: FoodItem){
        val bagItems = Paper.book().read(BAG_ITEM,ArrayList<FoodItem>())
        bagItems.add(foodItem)
        Log.v("Tag",foodItem.name)
        Paper.book().write(BAG_ITEM,bagItems)
    }
    fun getBagItems(): ArrayList<FoodItem> {
        return Paper.book().read(BAG_ITEM, ArrayList())
    }
}