package com.tuan88291.mvvmpattern.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuan88291.mvvmpattern.databinding.FooterBinding

class AdapterFooter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater
    private var binding: FooterBinding? = null
    init {
        inflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = FooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return 1
    }
    fun setState(state: AdapterStateLoad){
        when(state) {
            is AdapterStateLoad.Loading -> binding?.progressBar2?.visibility = View.VISIBLE
            is AdapterStateLoad.LoadingSuccess -> binding?.progressBar2?.visibility = View.GONE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
    internal inner class MyHolder(binding: FooterBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: FooterBinding? = null
        init {
            this.binding = binding
        }
    }
}