package com.tecnica.viewmodel.manager

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.tecnica.viewmodel.R

open class ErrorManager {

    fun catchException(exceptionType: ExceptionType, fragment: Fragment) {
        when (exceptionType) {
            is ExceptionType.UnknownHostException -> {
                fragment.context?.let {
                    dialogErrorConexion(it)
                }
            }

            else -> {
                fragment.context?.let {
                    dialogGenericError(it)
                }

            }
        }
    }

    private fun dialogErrorConexion(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.dialog_unknownhostexception_title)
        builder.setMessage(R.string.dialog_unknownhostexception_description)
        builder.setPositiveButton(R.string.dialog_unknownhostexception_accept) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun dialogGenericError(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.dialog_genericerror_title)
        builder.setMessage(R.string.dialog_genericerror_description)
        builder.setPositiveButton(R.string.dialog_genericerror_accept) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}