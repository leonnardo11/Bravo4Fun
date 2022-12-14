package com.ossmurfy.bravo4fun.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.AboutActivity
import com.ossmurfy.bravo4fun.EditProfileFragment
import com.ossmurfy.bravo4fun.LoginActivity
import com.ossmurfy.bravo4fun.R
import com.ossmurfy.bravo4fun.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        binding.editarPerfilButton.setOnClickListener {
            val frag = EditProfileFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()
        } 

        binding.meusPedidosButton.setOnClickListener {
            val frag = OrdersFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()
        }
        binding.sairButton.setOnClickListener {
            var i = Intent(context, LoginActivity::class.java)
            startActivity(i)
        }
        binding.meusPedidosButton2.setOnClickListener {
            var i = Intent(context, AboutActivity::class.java)
            startActivity(i)
        }

        return binding.root
    }

}