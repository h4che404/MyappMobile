@file:Suppress("UNREACHABLE_CODE")

package com.juancruz.myapp.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.juancruz.myapp.R

class ImcCalculatorActivity : AppCompatActivity() {


    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 60
    private var currentEdad: Int = 21
    private var currentHeight: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnMenos: FloatingActionButton
    private lateinit var btnMas: FloatingActionButton
    private lateinit var tvPeso: TextView
    private lateinit var tvEdad: TextView
    private lateinit var btnMenorEdad: FloatingActionButton
    private lateinit var btnMayorEdad: FloatingActionButton
    private lateinit var btnCalcular: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initListeners()
        initUI()
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setEdad()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnMenos = findViewById(R.id.btnMenos)
        btnMas = findViewById(R.id.btnMas)
        tvPeso = findViewById(R.id.tvPeso)
        tvEdad = findViewById(R.id.tvEdad)
        btnMenorEdad = findViewById(R.id.btnMenorEdad)
        btnMayorEdad = findViewById(R.id.btnMayorEdad)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = java.text.DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnMenos.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnMas.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnMenorEdad.setOnClickListener {
            currentEdad -= 1
            setEdad()
        }
        btnMayorEdad.setOnClickListener {
            currentEdad += 1
            setEdad()
        }
        btnCalcular.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc: Double = currentWeight / Math.pow(currentHeight.toDouble() / 100, 2.0)
        return df.format(imc).toDouble()
    }

    private fun setEdad() {
        tvEdad.text = currentEdad.toString()
    }

    private fun setWeight() {
        tvPeso.text = currentWeight.toString()
    }


    private fun changeGender() {
        // Si se seleccionó el botón masculino, deseleccionamos el femenino
        if (viewMale.isPressed) {
            isMaleSelected = true
            isFemaleSelected = false
        } else {
            // Si se seleccionó el botón femenino, deseleccionamos el masculino
            isMaleSelected = false
            isFemaleSelected = true
        }
        val TAG = "MiApp"
        Log.i(TAG, "Info: Género masculino seleccionado: ${isMaleSelected}")
        Log.i(TAG, "Info: Género femenino seleccionado: ${isFemaleSelected}")
    }

    private fun setGenderColor() {

        viewMale.setCardBackgroundColor(getColorBackground(isMaleSelected))
        viewFemale.setCardBackgroundColor(getColorBackground(isFemaleSelected))


        // Asegurarse de que los cambios se apliquen inmediatamente
        viewMale.invalidate()
        viewFemale.invalidate()

    }

    private fun getColorBackground(isViewSelected: Boolean): Int {

        val colorReference = if (isViewSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

}