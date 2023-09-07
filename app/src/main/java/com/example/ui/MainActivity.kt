package com.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.R
import com.example.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainActivityViewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.again.setOnClickListener{
            makeRequest()
        }
        makeRequest()
    }

    fun makeRequest() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val result = mainActivityViewModel.makeRequest()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                    binding.result.text = result
                }
            }
        }

    }
}