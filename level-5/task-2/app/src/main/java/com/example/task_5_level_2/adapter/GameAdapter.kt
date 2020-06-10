package com.example.task_5_level_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5_level_2.R
import com.example.task_5_level_2.models.Game
import kotlinx.android.synthetic.main.game.view.*
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games:List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvTitle.text = game.title
            itemView.tvPlatform.text = game.platform
            itemView.tvDate.text = getDateMonthYearString(game.releaseDate)
        }
    }

    private fun getDateMonthYearString(date: Date): String {
        val formatter = SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH)
        return "Release: " + formatter.format(date)
    }
}