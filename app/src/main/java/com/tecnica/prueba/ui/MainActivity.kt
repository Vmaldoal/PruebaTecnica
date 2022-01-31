package com.tecnica.prueba.ui

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.tecnica.prueba.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun getConstraintRoot(): ConstraintLayout {
        return binding.rootConstraint
    }
}