package com.jnlee.parsing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnlee.parsing.data.NaverRepository
import com.jnlee.parsing.domain.Review
import com.jnlee.parsing.presentation.LLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NaverViewModel(
//    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val naverRepository = NaverRepository()

    private val _uiState = MutableStateFlow<ReviewUiState>(ReviewUiState.Loading)
    val uiState: StateFlow<ReviewUiState> = _uiState

    init {
        naverRepository.getData()
        initCollect()
    }

    private fun initCollect() {
        viewModelScope.launch {
            naverRepository.reviewList.collectLatest {
                LLog.debug(it)
                _uiState.value = ReviewUiState.Success(it)
            }
        }
    }

    sealed class ReviewUiState {
        data class Success(val reviews: List<Review>) : ReviewUiState()
        object Loading : ReviewUiState()
        data class Error(val exception: Exception) : ReviewUiState()
    }
}