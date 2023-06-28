package com.jnlee.parsing.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jnlee.parsing.databinding.ItemNaverReviewBinding
import com.jnlee.parsing.domain.Review

class NaverRecyclerViewAdapter() :
    RecyclerView.Adapter<NaverRecyclerViewAdapter.NaverViewHolder>() {

    private var reviews: List<Review> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverViewHolder {
        val binding =
            ItemNaverReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NaverViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setReviews(reviews:List<Review>){
        this.reviews = reviews
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: NaverViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    class NaverViewHolder(private val binding: ItemNaverReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            //TODO : binding.iv
            binding.tvName.text = review.name
            binding.tvContent.text = review.content
            binding.tvDate.text = review.date
        }
    }
}