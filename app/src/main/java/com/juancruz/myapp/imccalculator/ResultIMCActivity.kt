package com.juancruz.myapp.imccalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juancruz.myapp.R
import com.juancruz.myapp.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnReCalcular.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvResultado.text = getString(R.string.BajoPeso)
                tvResultado.setTextColor(getColor(R.color.Flaco))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }

            in 18.51..24.99 -> {
                tvResultado.text = getString(R.string.Normal)
                tvResultado.setTextColor(getColor(R.color.Felicidades))
                tvDescription.text = getString(R.string.description_normal)
            }

            in 25.00..29.99 -> {
                tvResultado.text = getString(R.string.SobrePeso)
                tvResultado.setTextColor(getColor(R.color.SobrePeso))
                tvDescription.text = getString(R.string.description_sobre_peso)
            }

            in 30.00..99.00 -> {
                tvResultado.text = getString(R.string.Obesidad)
                tvResultado.setTextColor(getColor(R.color.obesidad))
                tvDescription.text = getString(R.string.description_obesidad)
            }

            else -> {//error
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
                tvResultado.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvResultado = findViewById(R.id.tvResultado)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalcular = findViewById(R.id.btnCalcular)
    }

}