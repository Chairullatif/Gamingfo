package com.chairullatif.gamingfo.home

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
import com.chairullatif.gamingfo.databinding.ActivityHomeBinding
import com.chairullatif.gamingfo.detail.DetailActivity
import com.chairullatif.gamingfo.favorite.FavoriteGameActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var gameAdapter: GameAdapter
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        initViewModel()
    }

    private fun initViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        homeViewModel.getListGame()
        homeViewModel.listGame.observe(this) { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        gameAdapter.setData(game.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            game.message ?: getString(R.string.something_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
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

        binding.rvGame.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.apply {
            ivFavorite.setOnClickListener {
                val intent = Intent(this@HomeActivity, FavoriteGameActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getListGame()
    }
}