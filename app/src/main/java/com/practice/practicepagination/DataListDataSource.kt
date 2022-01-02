package com.practice.practicepagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.practice.practicepagination.network.Data
import com.practice.practicepagination.network.DataService

class DataListDataSource(private val dataService : DataService) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        return try{
            val response = dataService.getData(page).data

            Log.i("MyTAG",page.toString()+" "+"Page Number "+ response.size.toString())
            LoadResult.Page(
                response,
                prevKey = if(page == 1) null else page-1,
                nextKey = if(response.isEmpty()) null else page+1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }

}