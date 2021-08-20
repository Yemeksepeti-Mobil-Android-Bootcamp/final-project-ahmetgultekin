package com.example.foodorderingapp.data.local

import android.content.Context
import android.util.Log
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.data.entity.FoodItem
import io.paperdb.Paper

class PaperManager(context : Context) {
    companion object{
        const val BAG_ITEM = "com.example.foodorderingapp.bag_item"
    }
    init {
        Paper.init(context)
    }

    fun addToBag(bagItem: BagItem){
        val bagItems = Paper.book().read(BAG_ITEM,ArrayList<BagItem>())
        bagItems.add(bagItem)
        Paper.book().write(BAG_ITEM,bagItems)
    }
    fun getBagItems(): ArrayList<BagItem> {
        return Paper.book().read(BAG_ITEM, ArrayList())
    }
    fun removeBagItem(bagItem: BagItem){
        val bagItems = Paper.book().read(BAG_ITEM,ArrayList<BagItem>())
        bagItems.remove(bagItem)
        Paper.book().write(BAG_ITEM,bagItems)
    }
}