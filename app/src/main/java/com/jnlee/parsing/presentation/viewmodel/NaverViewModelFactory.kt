package com.jnlee.parsing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NaverViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NaverViewModel::class.java)){
            return NaverViewModel() as T
        }
        throw IllegalArgumentException("Not found ViewModel class.")
    }
}