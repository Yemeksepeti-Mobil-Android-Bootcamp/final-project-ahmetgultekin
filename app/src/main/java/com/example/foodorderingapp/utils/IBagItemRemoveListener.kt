package com.example.foodorderingapp.utils

import com.example.foodorderingapp.data.entity.BagItem

interface IBagItemRemoveListener {
    fun onClick(bagItem: BagItem)
}