package com.tuan88291.mvvmpattern.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuan88291.mvvmpattern.BR
import com.tuan88291.mvvmpattern.data.local.model.DataChat
import com.tuan88291.mvvmpattern.databinding.ItemChatFriendBinding
import com.tuan88291.mvvmpattern.databinding.ItemChatYouBinding

class AdapterChat(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater
    internal var data = mutableListOf<DataChat>()
    private var id: Int? = null
    init {
        inflater = LayoutInflater.from(context)
    }
    fun setId(id: Int) {
     this.id = id
    }
    fun setData(data: MutableList<DataChat>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == id) {
            return MyHolder(ItemChatYouBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        return FriendHolder(ItemChatFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data.getOrElse(position) { data.getOrNull(0) }
        val type = holder.itemViewType
        if (type == id) {
            val mHolder = holder as MyHolder
            mHolder.bind(item!!)
        } else {
            val mHolder = holder as FriendHolder
            mHolder.bind(item!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return this.data[position].id
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal inner class MyHolder(binding: ItemChatYouBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemChatYouBinding? = null
        init {
            this.binding = binding
        }
        fun bind(item: Any) {
            this.binding?.setVariable(BR.userChat, item)
        }
    }
    internal inner class FriendHolder(binding: ItemChatFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemChatFriendBinding? = null
        init {
            this.binding = binding
        }
        fun bind(item: Any) {
            this.binding?.setVariable(BR.userChat, item)
        }
    }
}