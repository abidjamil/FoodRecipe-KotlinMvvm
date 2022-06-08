package com.example.foodrecipe.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity




object VideoUtils {
    fun playVideo(videoUrl: String, context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        context.startActivity(browserIntent)
    }
}