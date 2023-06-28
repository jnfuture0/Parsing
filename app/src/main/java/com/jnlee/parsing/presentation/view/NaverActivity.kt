package com.jnlee.parsing.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jnlee.parsing.databinding.ActivityNaverBinding
import com.jnlee.parsing.domain.Review
import com.jnlee.parsing.presentation.LLog
import com.jnlee.parsing.presentation.viewmodel.NaverViewModel
import com.jnlee.parsing.presentation.viewmodel.NaverViewModel.ReviewUiState
import com.jnlee.parsing.presentation.viewmodel.NaverViewModelFactory
import kotlinx.coroutines.launch

class NaverActivity : AppCompatActivity() {

    private var _binding: ActivityNaverBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NaverViewModel
    private val adapter = NaverRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNaverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()
        initCollect()

    }

    private fun initViews() {
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = adapter
    }

    private fun initCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is ReviewUiState.Success -> showReviews(uiState.reviews)
                        is ReviewUiState.Loading -> showProgress()
                        is ReviewUiState.Error -> showError(uiState.exception)
                    }
                }
            }
        }
    }

    private fun showReviews(reviews: List<Review>) {
        hideProgress()
        LLog.debug(reviews)
        adapter.setReviews(reviews)
    }

    private fun initViewModel() {
        try {
            val factory = NaverViewModelFactory()
            viewModel = ViewModelProvider(this, factory).get(NaverViewModel::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showProgress() {
        binding.groupProgress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.groupProgress.visibility = View.GONE
    }

    private fun showError(e: Exception) {
        e.printStackTrace()
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}