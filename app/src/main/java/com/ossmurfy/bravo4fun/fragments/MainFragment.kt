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
            binding.MainLayout.background
            binding.textViewMainTitle.text="LOLLAPALOOZA"
        }
        binding.imageViewRockInRio.setOnClickListener {
            //binding.MainLayout.background = "@drawable/knotfest"
            binding.textViewMainTitle.text="ROCK IN RIO"
        }

        binding.buttonHome.setOnClickListener {
            if(binding.textViewMainTitle.text=="KNOTFEST"){

            }else if(binding.textViewMainTitle.text=="LOLLAPALOOZA"){

            } else if(binding.textViewMainTitle.text=="ROCK IN RIO"){

            }
        }


        return binding.root
    }

}