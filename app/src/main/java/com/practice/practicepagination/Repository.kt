package com.practice.practicepagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.practice.practicepagination.network.Data
import com.practice.practicepagination.network.DataService
import com.practice.practicepagination.network.Users
import kotlinx.coroutines.flow.Flow

class Repository(private val dataService: DataService) {
    fun getResult(): Flow<PagingData<Data>>{
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = {DataListDataSource(dataService)}
        ).flow
    }
}