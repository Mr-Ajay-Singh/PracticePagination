package com.practice.practicepagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.practicepagination.databinding.ActivityMainBinding
import com.practice.practicepagination.network.DataService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var mAdapter : RecyclerViewAdapter
    lateinit var stateAdapter1: LoadStateAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mAdapter = RecyclerViewAdapter()
        stateAdapter1 = LoadStateAdapter1{mAdapter.retry()}

        binding.mainRecyclerview.apply{
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter = mAdapter.withLoadStateFooter(stateAdapter1)
        }

        val service = DataService()
        val repo = Repository(service)
        val factory = MainActivityViewModelFactory(repo)
        viewModel = ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)

        lifecycleScope.launch {
            viewModel.getAllData().collectLatest {
                binding.mainRecyclerview.isVisible = true
                mAdapter.submitData(it)
            }

        }

        mAdapter.addLoadStateListener { loadStates ->
            binding.showMoreData.isVisible = loadStates.source.refresh is LoadState.Loading
        }

    }
}