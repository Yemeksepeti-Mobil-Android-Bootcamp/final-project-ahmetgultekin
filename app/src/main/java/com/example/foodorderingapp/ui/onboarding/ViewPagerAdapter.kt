package com.example.foodorderingapp.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity,var fragments:ArrayList<Fragment>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int =  fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]



}