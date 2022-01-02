package com.practice.practicepagination

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.practicepagination.databinding.ActivityMainBinding
import com.practice.practicepagination.databinding.ListItemBinding
import com.practice.practicepagination.network.Data
import com.practice.practicepagination.network.DiffUtilCallback

class RecyclerViewAdapter(): PagingDataAdapter<Data, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.i("MYTAG",position.toString())
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemBinding>(inflator,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Data){
            //Log.i("MYTAG",data.email+" "+data.first_name)
            binding.emailList.text = data.email
            binding.nameList.text = data.first_name + " " + data.last_name
        }
    }
}