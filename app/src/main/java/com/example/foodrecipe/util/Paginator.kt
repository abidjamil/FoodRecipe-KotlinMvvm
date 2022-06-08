package com.example.foodrecipe.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.base.BaseAdapter

class Paginator(recyclerView: RecyclerView,
                private val isLoading: () -> Boolean,
                private val loadMore: (Int) -> Unit,):RecyclerView.OnScrollListener() {
    var currentPage: Int = 0
    private var loading = isLoading()

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        layoutManager?.let {
            loading = isLoading()
            //for pagination total results count will be in baseadapter
            val totalItemCount = (recyclerView.adapter as BaseAdapter<*>).totalCount

            val itemCount = it.itemCount
            val firstVisibleItemPosition = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> return
            }

            if (loading || itemCount == totalItemCount) return

            if (firstVisibleItemPosition >= itemCount - 1) {
                loading = true
                loadMore(++currentPage)
            }
        }
    }
}