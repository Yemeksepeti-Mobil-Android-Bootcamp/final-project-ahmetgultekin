package com.example.foodorderingapp.ui.bottomNavigation.foodDetail

import androidx.fragment.app.Fragment
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.databinding.FragmentFoodDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private val args : FoodDetailFragmentArgs by navArgs()
    private val viewModel : FoodDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        quantityListener()
    }

    fun initViews() {
        viewModel.setFood(args.foodClicked)
        viewModel.getFood().observe(viewLifecycleOwner,{ food ->
            binding.name.text = food.name
            binding.foodPrice.text = food.price
            Glide.with(binding.root).load(food.imageUrl).into(binding.foodImageView)
        })
        binding.submit.setOnClickListener {
            Toast.makeText(requireContext(),"Added to the bag",Toast.LENGTH_SHORT).show()
            viewModel.addToBag(args.foodClicked)
            var badge = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).getOrCreateBadge(R.id.bagFragment)
            badge.isVisible = true
            findNavController().navigateUp()
        }
    }


    fun quantityListener() {

        // creates the expandable list view

    }
}