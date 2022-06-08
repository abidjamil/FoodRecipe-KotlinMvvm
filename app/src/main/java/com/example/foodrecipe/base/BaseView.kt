package com.example.foodrecipe.base

import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.NavDirections
import com.example.foodrecipe.data.ErrorDescription



interface BaseView {
    fun setSoftInputMode(mode: Int)
    fun resetSoftInputMode()
    fun showKeyboard(editText: AppCompatEditText)
    fun hideKeyboard()
    fun noConnectivity()
    fun loaderVisibility(visibility: Boolean)
    fun showToast(message: String?)
    fun onApiError(errorDescription: ErrorDescription)
    fun navigateToDestination(direction: NavDirections)
    fun navigateToDestination(id: Int, args: Bundle)
    fun getNavHostId():Int?
    fun getLayoutId():Int
}