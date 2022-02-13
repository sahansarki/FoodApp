package com.example.foodapp.ui.extensions

import android.content.Context
import com.example.foodapp.utils.ProgressDialogHelper

fun <T: Context> T.dismissProgress(){
    ProgressDialogHelper.dismiss()
}

fun <T: Context> T.showProgress(){
    ProgressDialogHelper.showProgress(this)
}