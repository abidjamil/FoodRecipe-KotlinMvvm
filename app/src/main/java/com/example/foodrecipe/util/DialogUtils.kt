package com.example.foodrecipe.util


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.foodrecipe.R



object DialogUtils {

    private lateinit var dialog: AlertDialog

    fun createProgressDialog(context: Context, cancelable: Boolean): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.setContentView(R.layout.dialog_progress)
        dialog.setCanceledOnTouchOutside(cancelable)
        dialog.setCancelable(cancelable)
        return dialog
    }


}