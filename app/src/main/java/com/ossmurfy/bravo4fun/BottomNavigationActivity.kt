package com.ossmurfy.bravo4fun

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ossmurfy.bravo4fun.databinding.ActivityBottomNavigationBinding
import com.ossmurfy.bravo4fun.databinding.FragmentMainBinding
import com.ossmurfy.bravo4fun.fragments.MainFragment
import com.ossmurfy.bravo4fun.fragments.ProductFragment
import com.ossmurfy.bravo4fun.fragments.ScannerFragment
import com.ossmurfy.bravo4fun.fragments.perfilteste


class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(MainFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(MainFragment())
                R.id.profile -> replaceActivity(ProfileActivity())
                /*R.id.eventos -> replaceFragment()
                R.id.carrinho -> replaceFragment()
                */
                R.id.scanner -> replaceFragment(ScannerFragment())

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

    private fun replaceActivity(activity: Activity){
        var i = Intent(this, activity::class.java)
        startActivity(i)
    }
}


