package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuan88291.mvvmpattern.data.local.model.DetailUser
import com.tuan88291.mvvmpattern.view.adapter.AdapterFooter
import com.tuan88291.mvvmpattern.view.adapter.AdapterStateLoad
import com.tuan88291.mvvmpattern.view.adapter.HomeAdapter

class ListItemUser(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {
    private var mLayoutManager: LinearLayoutManager? = null
    private var mAdapter: HomeAdapter? = null
    private var mFooterAdapter: AdapterFooter? = null
    var onLoadmore: (() -> Unit)? = null

    init {
        setUpList(context)
    }

    private fun setUpList(context: Context) {
        mLayoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdapter(context).apply {
            stateRestorationPolicy = Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        mFooterAdapter = AdapterFooter(context)
        val mergedAdapter = ConcatAdapter(mAdapter, mFooterAdapter)
        layoutManager = mLayoutManager
        adapter = mergedAdapter
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && mLayoutManager?.findLastVisibleItemPosition()!! + VISIBLE_THRESHOLD >= mLayoutManager?.itemCount!!) {
                    onLoadmore?.invoke()
                }
            }
        })
    }
    fun setData(data: MutableList<DetailUser>) = mAdapter?.setData(data)
    fun setStateLoading(state: AdapterStateLoad) = mFooterAdapter?.setState(state)
    private companion object {
        private const val VISIBLE_THRESHOLD = 3
    }
}