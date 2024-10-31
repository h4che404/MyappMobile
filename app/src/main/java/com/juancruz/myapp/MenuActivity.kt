package com.juancruz.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juancruz.myapp.imccalculator.ImcCalculatorActivity
import com.juancruz.myapp.login.LoginActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val buttoninicioSesion = findViewById<Button>(R.id.buttonInicioSesion)
        val buttonIMCApp = findViewById<Button>(R.id.btnIMCApp)
        buttoninicioSesion.setOnClickListener { navToHola() }
        buttonIMCApp.setOnClickListener { navToIMC() }
    }
    private fun navToHola() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun navToIMC(){
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}