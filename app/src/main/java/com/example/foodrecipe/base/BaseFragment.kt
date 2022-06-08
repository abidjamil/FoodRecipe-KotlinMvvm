package com.example.foodrecipe.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import com.example.foodrecipe.data.ErrorDescription


abstract class BaseFragment<B : ViewDataBinding> : Fragment(), BaseView {

    abstract fun getViewModel(): BaseViewModel?

    abstract fun initFragment()
    lateinit var binding: B
    private var activity: BaseActivity<*>? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return if (!this::binding.isInitialized) {
            DataBindingUtil.inflate<B>(
                inflater,
                getLayoutId(),
                container, false
            ).apply {
                binding = this
                initFragment()
            }.root
        } else binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideKeyboard()

        getViewModel()?.outcomeLiveData?.observe(viewLifecycleOwner, Observer {
            when (it) {
                is com.example.foodrecipe.api.Result.Loading<*> -> {
                    if (it.showLoader)
                        loaderVisibility(true)
                }
                is com.example.foodrecipe.api.Result.Success<*> -> {
                    loaderVisibility(false)
                }
                is com.example.foodrecipe.api.Result.Failure<*> -> {
                    onApiError(it.e!!)
                    loaderVisibility(false)
                }
                is com.example.foodrecipe.api.Result.NetworkError<*> -> {
                    onApiError(it.e!!)
                    loaderVisibility(false)
                }
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            activity = context
        }
    }


    override fun setSoftInputMode(mode: Int) {
        activity?.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        activity?.resetSoftInputMode()
    }

    override fun showKeyboard(editText: AppCompatEditText) {
        activity?.showKeyboard(editText)
    }

    override fun hideKeyboard() {
        activity?.hideKeyboard()
    }


    override fun noConnectivity() {
        activity?.noConnectivity()
    }

    override fun loaderVisibility(visibility: Boolean) {
        activity?.loaderVisibility(visibility)
    }

    override fun showToast(message: String?) {
        activity?.showToast(message)
    }

    override fun onApiError(errorDescription: ErrorDescription) {
        activity?.onApiError(errorDescription)
    }

    override fun navigateToDestination(direction: NavDirections) {
        activity?.navigateToDestination(direction)
    }

    override fun navigateToDestination(id: Int, args: Bundle) {
        activity?.navigateToDestination(id, args)
    }

    override fun getNavHostId(): Int? {
     return   activity?.getNavHostId()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return activity
    }
}