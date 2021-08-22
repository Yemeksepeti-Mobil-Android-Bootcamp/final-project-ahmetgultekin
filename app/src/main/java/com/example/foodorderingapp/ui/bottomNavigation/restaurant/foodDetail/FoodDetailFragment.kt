package com.example.foodorderingapp.ui.bottomNavigation.restaurant.foodDetail

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.databinding.FragmentFoodDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

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
            binding.foodPrice.text = food.price.toString()
            Glide.with(binding.root).load(food.imageUrl).into(binding.foodImageView)
            var ingredients : String =""
            for(i in 0..food.ingredients.size-1){
                if(i == food.ingredients.size-1) ingredients += food.ingredients[i]
                else ingredients += "${food.ingredients[i]},"
            }
            binding.ingredients.text = ingredients
        })
        viewModel.getQuantity().observe(viewLifecycleOwner,{quantity ->
            binding.foodQuantity.text = "$quantity"
            binding.foodPrice.text = "${quantity * args.foodClicked.price} TL"
        })
        binding.addToBagButton.setOnClickListener {
            Toast.makeText(requireContext(),"Added to the bag",Toast.LENGTH_SHORT).show()
            val quantity = binding.foodQuantity.text.toString()
            Log.v("Tag",quantity)
            val bagItem = BagItem(args.restaurantName,quantity.toInt(),args.foodClicked)
            viewModel.addToBag(bagItem)
            var badge = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).getOrCreateBadge(R.id.bagFragment)
            badge.isVisible = true
            findNavController().navigateUp()
        }
    }


    fun quantityListener() {
        binding.increase.setOnClickListener {
            viewModel.increase()
        }
        binding.decrease.setOnClickListener {
            viewModel.decrease()
        }
    }
}