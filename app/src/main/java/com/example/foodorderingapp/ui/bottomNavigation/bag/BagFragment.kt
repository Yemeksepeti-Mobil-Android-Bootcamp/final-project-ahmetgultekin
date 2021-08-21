package com.example.foodorderingapp.ui.bottomNavigation.bag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.Accid
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.data.entity.Order
import com.example.foodorderingapp.databinding.FragmentBagBinding
import com.example.foodorderingapp.utils.IBagItemRemoveListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BagFragment : Fragment(),IBagItemRemoveListener {
    private var binding : FragmentBagBinding? = null
    private val viewModel : BagViewModel by viewModels()
    private lateinit var paymentMethod : String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBagBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }
    fun initViews(){
        binding!!.bagItemRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding!!.bagItemRecycler.setHasFixedSize(true)
        val adapter = BagItemAdapter()
        adapter.setListener(this)
        viewModel.getBagItems().observe(viewLifecycleOwner,{
            val arrayList = ArrayList<BagItem>(it)
            adapter.setBagItem(arrayList)
            binding!!.bagItemRecycler.adapter = adapter
            if(it.isNotEmpty()){
                initRadioButtons()
                binding!!.paymentMethodText.visibility = View.VISIBLE
                binding!!.savedAddressText.visibility = View.VISIBLE
                binding!!.selectAddressText.visibility = View.VISIBLE
                binding!!.orderButton.visibility = View.VISIBLE
            }
        })
        binding!!.orderButton.setOnClickListener {
            order()
        }
    }
     fun order(){

        viewModel.getBagItems().observe(viewLifecycleOwner,{ bagItems->
            val userInfo = viewModel.getUserInfo()
            val order = Order(Accid(userInfo!!.id), email = userInfo.email,bagItems[0].foodItem,"2021-08-21T14:54:59.751Z",paymentMethod,59.99,1,bagItems[0].restaurantName)
            viewModel.order(order).observe(viewLifecycleOwner,{
                if(it) {
                    Toast.makeText(requireContext(),"You ordered succesfully",Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                    // bagItmeları sil
                    }
                else Toast.makeText(requireContext(),"Your order failed.Please try again later!",Toast.LENGTH_SHORT).show()

            })
        })

    }
    fun initRadioButtons(){
        binding!!.radioGroup.visibility = View.VISIBLE
        binding!!.paymentRadioGroup.visibility = View.VISIBLE
        binding!!.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.savedAddress -> {
                    binding!!.newAddressTextField.visibility = View.VISIBLE
                }
                R.id.newAddress -> {
                    binding!!.newAddressTextField.visibility = View.VISIBLE
                }
            }
        }
        binding!!.paymentRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.creditCard -> paymentMethod = "Credit Card"
                R.id.atTheDoor -> paymentMethod = "At Door"
            }
        }
    }
    override fun onClick(bagItem: BagItem) {
        viewModel.removeBagItem(bagItem)
        val adapter = BagItemAdapter()
        adapter.setListener(this)
        viewModel.getBagItems().observe(viewLifecycleOwner,{
            val arrayList = ArrayList<BagItem>(it)
            adapter.setBagItem(arrayList)
            binding!!.bagItemRecycler.adapter = adapter
            if(it.isEmpty()){
                val badge = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).getOrCreateBadge(
                    R.id.bagFragment)
                badge.isVisible = it.isNotEmpty()
                binding!!.radioGroup.visibility = View.GONE
                binding!!.paymentRadioGroup.visibility = View.GONE
                binding!!.paymentMethodText.visibility = View.GONE
                binding!!.savedAddressText.visibility = View.GONE
                binding!!.selectAddressText.visibility = View.GONE
                binding!!.orderButton.visibility = View.GONE
            }
        })
    }

}