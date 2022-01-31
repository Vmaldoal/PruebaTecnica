package com.tecnica.prueba.ui

import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.tecnica.prueba.R
import com.tecnica.prueba.ui.component.CustomSpinner

abstract class BaseActivity : AppCompatActivity() {

    private val customSpinner: CustomSpinner by lazy { CustomSpinner(this) }

    private var loading: Boolean = false

    fun showLoading() {
        if (!loading) {
            getConstraintRoot()?.addView(
                customSpinner, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            )
        }
        loading = true
    }

    fun hideLoading() {
        if (loading) {
            getConstraintRoot()?.removeView(customSpinner)
            loading = false
        }
    }

    fun navigate(action: NavDirections, extras: FragmentNavigator.Extras) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action, extras)
    }

    abstract fun getConstraintRoot(): ConstraintLayout?
}