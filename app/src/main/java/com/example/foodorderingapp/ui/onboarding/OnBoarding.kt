package com.example.foodorderingapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentOnBoardingBinding
import com.example.foodorderingapp.data.local.SharedPreferencesManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoarding : Fragment() {
    private lateinit var binding : FragmentOnBoardingBinding
    private val listOfFragments = ArrayList<Fragment>()
    private val viewModel : OnBoardingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        listOfFragments.add(FirstOnBoarding())
        listOfFragments.add(SecondOnBoarding())
        listOfFragments.add(ThirdOnBoarding())
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity(),listOfFragments)
        binding.wormDotsIndicator.setViewPager2(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 0){
                    binding.prevButton.visibility = View.GONE
                }
                else if (position == listOfFragments.size - 1) {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.finish)
                    binding.nextButton.setOnClickListener {
                        findNavController().navigate(R.id.action_onBoarding_to_homeFragment)
                        viewModel.saveSituation(false)
                    }
                } else {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.next)
                    binding.nextButton.setOnClickListener {
                        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                    }
                    binding.prevButton.setOnClickListener{
                        binding.viewPager.currentItem = binding.viewPager.currentItem - 1
                    }

                }
            }
        })
    }


}