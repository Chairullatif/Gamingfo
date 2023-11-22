package com.chairullatif.gamingfo.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chairullatif.gamingfo.core.ui.GameAdapter
import com.chairullatif.gamingfo.detail.DetailActivity
import com.chairullatif.gamingfo.favorite.databinding.ActivityFavoriteGameBinding
import com.chairullatif.gamingfo.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteGameActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFavoriteGameBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var gameAdapter: GameAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        initView()

        initViewModel()
    }

    private fun initViewModel() {
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