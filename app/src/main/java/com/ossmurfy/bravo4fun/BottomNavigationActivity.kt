package com.ossmurfy.bravo4fun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ossmurfy.bravo4fun.databinding.ActivityBottomNavigationBinding
import com.ossmurfy.bravo4fun.fragments.profileteste
import com.ossmurfy.bravo4fun.fragments.testehome

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(testehome())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(testehome())
                R.id.profile -> replaceFragment(profileteste())
                else -> {

                }
            }

        true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}


