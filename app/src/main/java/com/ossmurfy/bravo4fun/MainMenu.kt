package com.ossmurfy.bravo4fun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        var resultado: Number = 0
        val texto: TextView = findViewById(R.id.number)

        fun mudaNumero() {
            resultado = 10;
            texto.toString().toInt()
            texto.text = resultado.toString()

        }
    }
}