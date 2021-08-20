package com.example.foodorderingapp.ui.bottomNavigation.bag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.databinding.FragmentBagBinding
import com.example.foodorderingapp.utils.IBagItemRemoveListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BagFragment : Fragment(),IBagItemRemoveListener {
    private var binding : FragmentBagBinding? = null
    private val viewModel : BagViewModel by viewModels()
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
        })
    }

    override fun onClick(bagItem: BagItem) {
        viewModel.removeBagItem(bagItem)
        val adapter = BagItemAdapter()
        viewModel.getBagItems().observe(viewLifecycleOwner,{
            val arrayList = ArrayList<BagItem>(it)
            adapter.setBagItem(arrayList)
            binding!!.bagItemRecycler.adapter = adapter
            if(it.isEmpty()){
                val badge = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).getOrCreateBadge(
                    R.id.bagFragment)
                badge.isVisible = it.isNotEmpty()
            }
        })
    }
}