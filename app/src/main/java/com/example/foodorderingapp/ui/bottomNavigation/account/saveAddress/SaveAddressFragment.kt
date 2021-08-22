package com.example.foodorderingapp.ui.bottomNavigation.account.saveAddress

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.AddressRequest
import com.example.foodorderingapp.databinding.FragmentSaveAddressBinding
import com.example.foodorderingapp.ui.bottomNavigation.restaurant.RestaurantAdapter
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveAddressFragment : Fragment() {
    private lateinit var binding : FragmentSaveAddressBinding
    private val viewModel : SavedAddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSaveAddressBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = viewModel.getUserInfo()
        Log.v("Tag",user.toString())
        binding.savedAddressButton.setOnClickListener {
            val addressRequest = AddressRequest(user!!.id,binding.newAddressTextField.editableText.toString())
            viewModel.updateAddress(addressRequest).observe(viewLifecycleOwner,{
                Log.v("Tagg",it.toString())
                when (it.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(),"Error while saving the address",Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.SUCCESS -> {
                        it.data?.let { responseUser ->
                            user.address = responseUser.address
                            viewModel.saveUserInfo(user)
                            findNavController().navigateUp()
                        }

                    }
                }

            })

        }
    }


}