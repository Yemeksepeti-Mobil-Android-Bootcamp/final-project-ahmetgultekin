package com.example.foodorderingapp.utils

private val categories = arrayListOf<String>("Pide&Lahmacun","All","Pizza","Hamburger","Kebab","SeaFood","Breakfast")
private val sections = arrayListOf<String>("Profile","My Address","Orders")
fun getCategory() : ArrayList<String>{
    return categories
}
fun getSections() : ArrayList<String>{
    return sections
}