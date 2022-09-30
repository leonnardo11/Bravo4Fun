package com.ossmurfy.bravo4fun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossmurfy.bravo4fun.databinding.FragmentEditProfileBinding
import com.ossmurfy.bravo4fun.databinding.FragmentProfileBinding
import com.ossmurfy.bravo4fun.fragments.ProfileFragment
import java.util.zip.Inflater

class EditProfileFragment : Fragment() {
    lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater)

        binding.salvarButton.setOnClickListener {
            val frag = ProfileFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, frag)?.commit()
        }
        return binding.root
    }


}