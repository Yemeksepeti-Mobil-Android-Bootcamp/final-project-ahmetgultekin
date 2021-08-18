package com.example.foodorderingapp.ui.bottomNavigation.restaurant

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.RestaurantItem
import com.example.foodorderingapp.databinding.FragmentRestaurantBinding
import com.example.foodorderingapp.utils.IRestaurantListener
import com.example.foodorderingapp.utils.getCategory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment(),IRestaurantListener {
    private lateinit var binding : FragmentRestaurantBinding
    private val restaurant = ArrayList<RestaurantItem>()
    private  var categories = ArrayList<String>()
    private val viewModel : RestaurantViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantBinding.inflate(inflater,container,false)
        Log.v("Tag","onCreateView")

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("Tag","onViewCreated")
        initViews()
    }
    fun initViews(){
        categories = getCategory()
            categories.forEach {
                addChip(it,binding.chipGroup)
            }
            Log.v("Tag","Size ${binding.chipGroup.size}")
        restaurant.add(RestaurantItem("Pizza Max","İzmir","Pizza,Burger","","","https://avechotel.com.tr/wp-content/uploads/2020/06/carne16.jpg","","",""))
        restaurant.add(RestaurantItem("Pizza Max","İzmir","Pizza,Burger","","","https://avechotel.com.tr/wp-content/uploads/2020/06/carne16.jpg","","",""))
        restaurant.add(RestaurantItem("Pizza Max","İzmir","Pizza,Burger","","","https://avechotel.com.tr/wp-content/uploads/2020/06/carne16.jpg","","",""))
        restaurant.add(RestaurantItem("Pizza Max","İzmir","Pizza,Burger","","","https://avechotel.com.tr/wp-content/uploads/2020/06/carne16.jpg","","",""))
        val adapter = RestaurantAdapter()
        adapter.setRestaurants(restaurant)
        binding.restaurantRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.restaurantRecycler.setHasFixedSize(true)
        binding.restaurantRecycler.adapter = adapter
        adapter.setListener(this)
    }


    override fun onClick(restaurantItem: RestaurantItem) {
        val action = RestaurantFragmentDirections.actionRestaurantFragmentToRestaurantDetailFragment(restaurantItem)

        findNavController().navigate(action)
    }
    private fun addChip(pItem: String, pChipGroup: ChipGroup) {
        val lChip = layoutInflater.inflate(R.layout.single_chip_layout, binding.chipGroup, false) as Chip
        if(pItem == "All"){
            lChip.isChecked = true
        }
        lChip.text = pItem
        lChip.setTextColor(resources.getColor(R.color.black))
        lChip.textSize = 18F
        pChipGroup.addView(lChip, pChipGroup.childCount - 1)
    }


}