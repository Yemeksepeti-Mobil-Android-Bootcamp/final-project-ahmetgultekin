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
import com.example.foodorderingapp.data.entity.RestaurantResponseItem
import com.example.foodorderingapp.databinding.FragmentRestaurantBinding
import com.example.foodorderingapp.utils.IRestaurantListener
import com.example.foodorderingapp.utils.Resource
import com.example.foodorderingapp.utils.getCategory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment(),IRestaurantListener {
    private lateinit var binding : FragmentRestaurantBinding
  //  private val restaurant = ArrayList<RestaurantItem>()
    private  var categories = ArrayList<String>()
    private val viewModel : RestaurantViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        selectedCategory()
        viewModel.getAllRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    val adapter = RestaurantAdapter()
                    binding.progressBar.visibility = View.GONE
                    adapter.setRestaurants(it.data!!)
                    binding.restaurantRecycler.adapter = adapter
                    adapter.setListener(this)
                }
            }
        })

    }
    fun initViews(){
        categories = getCategory()
            categories.forEach {
                addChip(it,binding.chipGroup)
            }
        binding.restaurantRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.restaurantRecycler.setHasFixedSize(true)
    }
    private fun selectedCategory() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = binding.chipGroup.findViewById<Chip>(binding.chipGroup.checkedChipId)
            val clickedCategory :String? = chip?.text.toString().lowercase()
            if(clickedCategory != null) {
                if (clickedCategory == "all") {
                    viewModel.getAllRestaurants().observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            Resource.Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                            }
                            Resource.Status.SUCCESS -> {
                                val adapter = RestaurantAdapter()
                                binding.progressBar.visibility = View.GONE
                                adapter.setRestaurants(it.data!!)
                                binding.restaurantRecycler.setHasFixedSize(true)
                                binding.restaurantRecycler.adapter = adapter
                                adapter.setListener(this)
                            }
                        }
                    })
                } else {
                    viewModel.getRestaurantByCategory(clickedCategory).observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            Resource.Status.ERROR -> {
                                Log.v("Tag", it.message!!)
                                binding.progressBar.visibility = View.GONE
                            }
                            Resource.Status.SUCCESS -> {
                                Log.v("Tag", "Succes")
                                binding.progressBar.visibility = View.GONE
                                val arrayList =
                                    ArrayList<RestaurantResponseItem>(it.data!!.restaurants)
                                Log.v("Tag", "Size of the array ${it.data.restaurants.size}")
                                val adapter = RestaurantAdapter()
                                binding.progressBar.visibility = View.GONE
                                adapter.setRestaurants(arrayList)
                                binding.restaurantRecycler.setHasFixedSize(true)
                                binding.restaurantRecycler.adapter = adapter
                                adapter.setListener(this)
                            }
                        }
                    })
                }
            }



        }
    }


    override fun onClick(restaurantItem: RestaurantResponseItem) {
        val action = RestaurantFragmentDirections.actionRestaurantFragmentToRestaurantDetailFragment(restaurantItem)
        findNavController().navigate(action)
    }
    private fun addChip(pItem: String, pChipGroup: ChipGroup) {
        val lChip = layoutInflater.inflate(R.layout.single_chip_layout, binding.chipGroup, false) as Chip
        lChip.text = pItem
        if(pItem == "All"){
            lChip.isChecked = true
        }
        lChip.setTextColor(resources.getColor(R.color.black))
        lChip.textSize = 18F
        pChipGroup.addView(lChip, pChipGroup.childCount - 1)
    }


}