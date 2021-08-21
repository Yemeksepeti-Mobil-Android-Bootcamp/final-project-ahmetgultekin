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
        binding.logout.setOnClickListener {
            viewModel.setToken("")
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp()
        }
    }
    fun initViews(){
        val sections = getSections()
        binding.accountSections.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,
            sections)
        binding.accountSections.setOnItemClickListener { parent, view, position, id ->
            when(sections[position]){
                "Orders" -> findNavController().navigate(R.id.action_accountFragment_to_ordersFragment2)
            }
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