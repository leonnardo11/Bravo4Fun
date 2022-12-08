package com.ossmurfy.bravo4fun.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.BottomNavigationActivity
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

       binding.imageViewKnotFest.setOnClickListener {

           //binding.MainLayout.background = "@drawable/knotfest"
           binding.textViewMainTitle.text="KNOTFEST"

        }
        binding.imageViewLollaPalooza.setOnClickListener{
            //binding.MainLayout.background = "@drawable/lollapalooza"
            binding.textViewMainTitle.text="LOLLAPALOOZA"
        }
        binding.imageViewRockInRio.setOnClickListener {
            //binding.MainLayout.background = "@drawable/rock_in_rio"
            binding.textViewMainTitle.text="ROCK IN RIO"
        }

        binding.buttonHome.setOnClickListener {
            if(binding.textViewMainTitle.text=="KNOTFEST"){
                val frag = ProductFragment(3)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()
            }else if(binding.textViewMainTitle.text=="LOLLAPALOOZA"){
                val frag = ProductFragment(87)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()
            } else if(binding.textViewMainTitle.text=="ROCK IN RIO"){
                val frag = ProductFragment(94)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()
            }
        }


        return binding.root
    }

}