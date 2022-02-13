package com.example.foodapp.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.foodapp.R

object ProgressDialogHelper {

    private var progressDialog: AlertDialog? = null

    fun showProgress(context: Context) {

        if (progressDialog != null) {
            return
        }

        val builder = AlertDialog.Builder(context)
        val newFeaturesView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)

        builder.setView(newFeaturesView)
        progressDialog = builder.create()
        progressDialog!!.show()
        progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun dismiss() {
        progressDialog?.dismiss()
        progressDialog = null
    }

}