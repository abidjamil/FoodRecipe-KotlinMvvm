package com.example.foodrecipe.base

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView



@Suppress("unused", "LeakingThis")
abstract class BaseViewHolder<V:ViewDataBinding>(private val view: V) :
    RecyclerView.ViewHolder(view.root),View.OnClickListener,View.OnLongClickListener {
   init {
       view.root.setOnClickListener(this)
       view.root.setOnLongClickListener(this)
   }
    abstract fun bindData(data:Any,position:Int)
    fun view()=view.root
    fun binding()=view
    fun context(): Context =view.root.context

}