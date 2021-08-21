package com.example.foodorderingapp.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.RegisterRequest
import com.example.foodorderingapp.databinding.FragmentRegisterBinding
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private val viewModel : RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        binding.buttonRegister.setOnClickListener {
            val registerRequest = RegisterRequest(binding.textInputEditTextEmail.editableText.toString(),
                binding.textInputEditTextPassword.editableText.toString(),
                binding.textInputEditTextName.editableText.toString(),
                binding.textInputEditTextSurname.editableText.toString(),
                binding.textInputEditTextPhoneNumber.editableText.toString().toLong())
            viewModel.register(registerRequest).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING ->{

                    }
                    Resource.Status.ERROR ->{
                        Log.v("Tag",it.message!!)
                    }
                    Resource.Status.SUCCESS ->{
                        findNavController().navigate(R.id.action_registerFragment_to_bottomNavigationFragment)
                        viewModel.setToken(it.data!!.token)
                        viewModel.saveUserInfo(it.data)
                    }
                }
            })
        }
    }
}