package com.practice.practicepagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.practice.practicepagination.network.Data
import com.practice.practicepagination.network.DataService
import com.practice.practicepagination.network.Users
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

     val result: Flow<PagingData<Data>> = repository.getResult().cachedIn(viewModelScope)


     fun getAllData() : Flow<PagingData<Data>>{
          return result
     }
}