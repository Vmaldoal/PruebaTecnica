package com.tecnica.prueba.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import com.tecnica.viewmodel.manager.ErrorManager
import org.koin.android.ext.android.inject


abstract class BaseFragment: Fragment() {
    internal val errorManager: ErrorManager by inject()

    fun showLoading() {
        (activity as BaseActivity).showLoading()
    }

    fun hideLoading() {
        (activity as BaseActivity).hideLoading()
    }

    fun navigator(action: NavDirections, extras: FragmentNavigator.Extras) {
        (activity as BaseActivity).navigate(action, extras)
    }

}