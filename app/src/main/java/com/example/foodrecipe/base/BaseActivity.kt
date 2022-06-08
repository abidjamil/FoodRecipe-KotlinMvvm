package com.example.foodrecipe.base


import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.foodrecipe.R
import com.example.foodrecipe.data.ErrorDescription
import com.example.foodrecipe.util.DialogUtils


abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), BaseView {
    abstract fun hasConnectivity(connectivity: Boolean)
    abstract fun getViewModel(): BaseViewModel?
    abstract fun init()

    private lateinit var dialog: Dialog
    lateinit var binding: B
    private var originalSoftInputMode: Int? = null
    private lateinit var inputManager: InputMethodManager
    private lateinit var connectivityManager: ConnectivityManager
    private var availableNetwork: Network? = null
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        dialog = DialogUtils.createProgressDialog(this, false)
        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        getViewModel()?.outcomeLiveData?.observe(this, Observer {
            when (it) {
                is com.example.foodrecipe.api.Result.Loading<*> -> {
                    if (it.showLoader)
                        loaderVisibility(true)
                }
                is com.example.foodrecipe.api.Result.Success<*> -> loaderVisibility(false)
                is com.example.foodrecipe.api.Result.Failure<*> -> loaderVisibility(false)
                is com.example.foodrecipe.api.Result.NetworkError<*> -> loaderVisibility(false)

            }

        })
        registerNetworkCallback()
        getNavHostId()?.let {
            val navHostFragment = supportFragmentManager.findFragmentById(it) as NavHostFragment
            navController = navHostFragment.navController
        }

        init()
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }

    override fun setSoftInputMode(mode: Int) {
        originalSoftInputMode = window.attributes.softInputMode
        window.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        originalSoftInputMode?.let {
            window.setSoftInputMode(it)
        }
    }

    override fun showKeyboard(editText: AppCompatEditText) {
        editText.post {
            editText.requestFocus()
            inputManager.showSoftInput(editText.rootView, InputMethodManager.SHOW_FORCED)
        }
    }

    override fun hideKeyboard() {
        this.currentFocus?.let {
            inputManager.hideSoftInputFromWindow(it.applicationWindowToken, 0)
        }
    }


    override fun noConnectivity() {
        showToast(getString(R.string.no_internet_connectivity))
    }

    override fun loaderVisibility(visibility: Boolean) {
        if (::dialog.isInitialized) {
            if (visibility) {
                if (!dialog.isShowing)
                    dialog.show()
            } else {
                dialog.dismiss()
            }
        }
    }

    override fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onApiError(errorDescription: ErrorDescription) {
        showToast(errorDescription.message)

    }

    override fun navigateToDestination(direction: NavDirections) {
        if (::navController.isInitialized) {
            navController.navigate(direction)
        } else {
            throw IllegalAccessException("Nav Controller not set in activity")
        }

    }

    override fun navigateToDestination(id: Int, args: Bundle) {
        if (::navController.isInitialized) {
            navController.navigate(id, args)
        } else {
            throw IllegalAccessException("Nav Controller not set in activity")
        }

    }


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            availableNetwork = network
            runOnUiThread { hasConnectivity(true) }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            if (network == availableNetwork) {
                runOnUiThread { hasConnectivity(false) }
            }
        }
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}