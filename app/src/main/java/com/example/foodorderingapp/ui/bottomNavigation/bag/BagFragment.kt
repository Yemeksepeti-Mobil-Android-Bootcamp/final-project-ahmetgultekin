package com.example.foodorderingapp.ui.bottomNavigation.bag

import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
        val user = viewModel!!.getUserInfo()
        Log.v("Tag",user.toString())
        if(user?.address.isNullOrEmpty())
            binding!!.savedAddressText.text = "You do not have saved address.Please go to Account and save your address"
        else{
            binding!!.savedAddressText.text = user!!.address[0]
        }

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
                binding!!.emptyImage.visibility = View.GONE
                binding!!.emptyText.visibility = View.GONE
            }
        })
        binding!!.orderButton.setOnClickListener {
            order()
        }
    }
     fun order(){
         Log.v("Tag","assad ${binding!!.paymentRadioGroup.checkedRadioButtonId}")

             viewModel.getBagItems().observe(viewLifecycleOwner,{ bagItems->
                 val userInfo = viewModel.getUserInfo()
                 val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                 val currentDate = sdf.format(Date())
                 val order = Order(Accid(userInfo!!.id), email = userInfo.email,bagItems[0].foodItem,"2021-08-21T14:54:59.751+00:00",paymentMethod,bagItems[0].foodItem.price,1,bagItems[0].restaurantName)
                 viewModel.order(order)
                 Toast.makeText(requireContext(),"You ordered succesfully",Toast.LENGTH_SHORT).show()
                 viewModel.deleteBag()
                 var badge = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).getOrCreateBadge(
                     R.id.bagFragment)
                 badge.isVisible = false
                 Handler(getMainLooper()).postDelayed({
                     findNavController().navigateUp()
                 }, 1000)

             })

    }
    fun initRadioButtons(){
        binding!!.radioGroup.visibility = View.VISIBLE
        binding!!.paymentRadioGroup.visibility = View.VISIBLE
        binding!!.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.savedAddress -> {
                    binding!!.newAddressTextField.visibility = View.GONE
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
                binding!!.emptyImage.visibility = View.VISIBLE
                binding!!.emptyText.visibility = View.VISIBLE
            }
        })
    }

}