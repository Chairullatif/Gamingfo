package com.chairullatif.gamingfo.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chairullatif.gamingfo.R
import com.chairullatif.gamingfo.core.data.Resource
import com.chairullatif.gamingfo.core.ui.GameAdapter
import com.chairullatif.gamingfo.core.ui.ViewModelFactory
import com.chairullatif.gamingfo.databinding.ActivityFavoriteGameBinding
import com.chairullatif.gamingfo.detail.DetailActivity
import com.chairullatif.gamingfo.home.HomeViewModel

class FavoriteGameActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFavoriteGameBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    private lateinit var gameAdapter: GameAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        initViewModel()
    }

    private fun initViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        favoriteViewModel.favoriteGames.observe(this) { game ->
            if (game != null) {
                gameAdapter.setData(game)
            }
        }

    }

    private fun initView() {
        gameAdapter = GameAdapter()
        gameAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.favoriteGames
    }
}