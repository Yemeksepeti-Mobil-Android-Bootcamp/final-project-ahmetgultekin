package com.example.foodorderingapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.LoginRequest
import com.example.foodorderingapp.databinding.FragmentLoginBinding
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        val loginRequest = LoginRequest(binding.emailTextField.editText.toString(),binding.passwordTextField.editText.toString())
        binding.toRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(loginRequest).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING ->{

                    }
                    Resource.Status.ERROR ->{
                        Log.v("Tag",it.message!!)
                    }
                    Resource.Status.SUCCESS ->{
                        findNavController().navigate(R.id.action_loginFragment_to_bottomNavigationFragment)
                        viewModel.setToken(it.data!!.token)
                    }
                }
            })
        }
    }
}