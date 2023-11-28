package com.chairullatif.gamingfo.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chairullatif.gamingfo.core.R
import com.chairullatif.gamingfo.core.databinding.ItemGameBinding
import com.chairullatif.gamingfo.core.domain.model.GameModel

class GameItemAdapter : ListAdapter<GameModel, GameItemAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((GameModel) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.backgroundImage)
                    .into(ivGame)
                tvTitle.text = data.name
                tvRelease.text = itemView.context.getString(R.string.release, data.released)
                tvRating.text = itemView.context.getString(R.string.rating, data.rating.toString())
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameModel>() {
            override fun areItemsTheSame(oldItem: GameModel, newItem: GameModel): Boolean {
                return oldItem.gameId == newItem.gameId
            }

            override fun areContentsTheSame(oldItem: GameModel, newItem: GameModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}