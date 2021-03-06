package com.example.foodorderingapp.ui.bottomNavigation.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.databinding.FragmentAccountBinding
import com.example.foodorderingapp.utils.getSections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private lateinit var binding : FragmentAccountBinding
    private val viewModel : AccountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUserInfo()
        //deletes bag and logged in user when log out
        binding.logout.setOnClickListener {
            viewModel.deleteBag()
            viewModel.deleteUser()
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp()
        }
    }
    fun initViews(){
        binding.newAddressButton.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_saveAddressFragment)
        }
        val user = viewModel.getUserInfo()
        if(user!!.address.size > 0){
            binding.noAddressesText.visibility = View.GONE
            binding.newAddressButton.visibility = View.GONE
            binding.savedAddress.text = user.address[0]
            Log.v("Tag",user.toString())
        }
        binding.toOrdersButton.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_ordersFragment2)
        }
    }

    fun setUserInfo(){
        val user : LoginResponse? = viewModel.getUserInfo()
        binding.accountEmail.text = user?.email
        binding.accountName.text = "${user?.name} ${user?.lastname}"
        binding.accountPhone.text = "Tel: ${user?.phone}"
        Log.v("Tag",user!!.id)
    }


}