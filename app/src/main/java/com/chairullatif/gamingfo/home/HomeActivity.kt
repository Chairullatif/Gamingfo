package com.chairullatif.gamingfo.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chairullatif.gamingfo.R
import com.chairullatif.gamingfo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}