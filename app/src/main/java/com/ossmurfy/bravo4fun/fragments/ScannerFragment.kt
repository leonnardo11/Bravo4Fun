package com.ossmurfy.bravo4fun.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.EditProfileFragment
import com.ossmurfy.bravo4fun.LoginActivity
import com.ossmurfy.bravo4fun.QrCodeActivity
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.ActivityScannerBinding
import com.ossmurfy.bravo4fun.databinding.FragmentProfileBinding
import com.ossmurfy.bravo4fun.databinding.FragmentScannerBinding


class ScannerFragment : Fragment() {
    lateinit var binding: FragmentScannerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScannerBinding.inflate(inflater)


        binding.button2.setOnClickListener {
            var i = Intent(context, QrCodeActivity::class.java)
            startActivity(i)
        }

        return binding.root
    }
}