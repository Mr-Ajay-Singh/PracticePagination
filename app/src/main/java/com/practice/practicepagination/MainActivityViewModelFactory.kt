package com.practice.practicepagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.practicepagination.network.DataService

class MainActivityViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(repository) as T
        }
        throw IllegalAccessException("Not Available")
    }
}