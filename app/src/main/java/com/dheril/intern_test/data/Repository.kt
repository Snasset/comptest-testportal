package com.dheril.intern_test.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dheril.intern_test.data.response.DataItem
import com.dheril.intern_test.data.response.UsersResponse
import com.dheril.intern_test.data.retrofit.ApiConfig
import com.google.gson.Gson
import retrofit2.HttpException

class Repository {

    fun getUserList(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12
            ),
            pagingSourceFactory = {
                UserPagingSource()
            }
        ).liveData
    }


}