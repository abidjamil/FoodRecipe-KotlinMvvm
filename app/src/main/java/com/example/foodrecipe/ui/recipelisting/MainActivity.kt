package com.example.foodrecipe.ui.recipelisting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodrecipe.R
import com.example.foodrecipe.base.BaseActivity
import com.example.foodrecipe.base.BaseViewModel
import com.example.foodrecipe.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getNavHostId(): Int? {
        return R.id.nav_host
    }

    override fun getLayoutId(): Int {
      return R.layout.activity_main
    }

    override fun hasConnectivity(connectivity: Boolean) {
        if (!connectivity)
            noConnectivity()
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    override fun init() {

    }
}