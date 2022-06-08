package com.example.foodrecipe.base

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.foodrecipe.api.Result


abstract class BaseViewModel : ViewModel() {
//  companion  object {
//      var job = Job()
//  }
    var outcomeLiveData = MediatorLiveData<Result<*>>()

    // Cancel the job when the view model is destroyed
//    override fun onCleared() {
//        super.onCleared()
//        job.cancel()
//    }
}