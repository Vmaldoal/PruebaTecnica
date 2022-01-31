package com.tecnica.prueba.ui.component

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.tecnica.prueba.R

class CustomSpinner(context: Context) : ConstraintLayout(context) {

    init {
        View.inflate(context, R.layout.custom_spinner_component ,this)
    }
}