package com.practice.practicepagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.practice.practicepagination.databinding.StateFooterBinding

class LoadStateAdapter1 (private val retry:()->Unit) : LoadStateAdapter<FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val binding = DataBindingUtil.inflate<StateFooterBinding>(LayoutInflater.from(parent.context),R.layout.state_footer,parent,false)
        return FooterViewHolder(binding,retry)
    }
}