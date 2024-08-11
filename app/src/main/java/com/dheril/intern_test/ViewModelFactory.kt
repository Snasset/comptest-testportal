package com.dheril.intern_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dheril.intern_test.data.Repository
import com.dheril.intern_test.screen.thirdscreen.ThirdScreenViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ThirdScreenViewModel::class.java) -> {
                ThirdScreenViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

        }
    }
}