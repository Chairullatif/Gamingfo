package com.chairullatif.gamingfo.detail

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chairullatif.gamingfo.R
import com.chairullatif.gamingfo.core.domain.model.GameModel
import com.chairullatif.gamingfo.core.ui.ViewModelFactory
import com.chairullatif.gamingfo.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailGameViewModel: DetailGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailGame = intent.parcelable<GameModel>(EXTRA_DATA)
        initDetailView(detailGame)

        initViewModel(detailGame)
    }

    private fun initViewModel(detailGame: GameModel?) {
        if (detailGame != null) {
            val factory = ViewModelFactory.getInstance(this)
            detailGameViewModel = ViewModelProvider(this, factory)[DetailGameViewModel::class.java]

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun initDetailView(detailGame: GameModel?) {
        if (detailGame != null) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(detailGame.backgroundImage)
                    .into(ivBackgroundHeader)
                tvTitle.text = detailGame.name
                tvRelease.text = "Release: " + detailGame.released
                tvRating.text = "⭐" + detailGame.rating.toString()
//                tvDescription.text = detailGame.description

                btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageResource(R.drawable.ic_bookmark_active)
        } else {
            binding.fab.setImageResource(R.drawable.ic_bookmark_inactive)
        }
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}