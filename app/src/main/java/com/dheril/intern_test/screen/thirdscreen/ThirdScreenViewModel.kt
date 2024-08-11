package com.dheril.intern_test.screen.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dheril.intern_test.data.Repository
import com.dheril.intern_test.data.response.DataItem

class ThirdScreenViewModel(private val repository: Repository): ViewModel() {

    val listUser: LiveData<PagingData<DataItem>> =
        repository.getUserList().cachedIn(viewModelScope)
}