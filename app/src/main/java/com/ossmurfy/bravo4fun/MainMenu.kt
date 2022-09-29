package com.ossmurfy.bravo4fun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ossmurfy.bravo4fun.databinding.ActivityAboutBinding
import com.ossmurfy.bravo4fun.databinding.ActivityMainMenuBinding
import com.ossmurfy.bravo4fun.fragments.ProductFragment

class MainMenu : AppCompatActivity() {
    lateinit var bindingMainMenu: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainMenu = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(bindingMainMenu.root)
    }

}