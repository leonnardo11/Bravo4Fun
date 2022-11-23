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

       binding.buttonHome.setOnClickListener {
            val frag = ProductFragment(2)
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()

        }


        return binding.root
    }

}