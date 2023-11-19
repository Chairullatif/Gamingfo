package com.chairullatif.gamingfo.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chairullatif.gamingfo.R
import com.chairullatif.gamingfo.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}