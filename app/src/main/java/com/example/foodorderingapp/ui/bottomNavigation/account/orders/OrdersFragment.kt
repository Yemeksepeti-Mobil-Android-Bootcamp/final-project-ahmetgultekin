package com.example.foodorderingapp.ui.bottomNavigation.account.orders

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.OrderResponse
import com.example.foodorderingapp.data.entity.OrderResponseItem
import com.example.foodorderingapp.databinding.FragmentOrdersBinding
import com.example.foodorderingapp.ui.bottomNavigation.restaurant.RestaurantAdapter
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrdersFragment : Fragment() {
    private lateinit var binding : FragmentOrdersBinding
    private val viewModel : OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        val adapter = OrdersAdapter()
        binding.ordersRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.ordersRecycler.setHasFixedSize(true)

        //fetching orders and displayin by recyclerview
        viewModel.getOrders(viewModel.getUserInfo()!!.id).observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.orderProgressBar.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {

                    binding.orderProgressBar.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    adapter.setOrder(it.data!!)
                    binding.ordersRecycler.adapter = adapter
                    binding.orderProgressBar.visibility = View.GONE

                }
            }
        })
    }


}