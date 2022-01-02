package com.practice.practicepagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.practice.practicepagination.databinding.StateFooterBinding

class FooterViewHolder(private val binding: StateFooterBinding, retry:() -> Unit): RecyclerView.ViewHolder(binding.root) {

    init{
        binding.showMoreData.setOnClickListener{
            retry
        }
    }

    fun bind(loadState: LoadState){
        if(loadState is LoadState.Loading){
            binding.showMoreData.text = "Show More"
            binding.showMoreData.visibility = View.VISIBLE
        }else if(loadState is LoadState.Error){
            binding.showMoreData.text = "No More Data to Show"
            binding.showMoreData.visibility = View.VISIBLE
            binding.showMoreData.setOnClickListener{

            }
        }
    }

    companion object{
        fun create(binding: StateFooterBinding, retry: ()-> Unit):FooterViewHolder{
            return FooterViewHolder(binding,retry)
        }
    }

}