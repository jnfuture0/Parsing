package com.jnlee.parsing.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jnlee.parsing.databinding.ActivityMainBinding
import com.jnlee.parsing.presentation.view.NaverActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNaver.setOnClickListener {
            val intent = Intent(this, NaverActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}