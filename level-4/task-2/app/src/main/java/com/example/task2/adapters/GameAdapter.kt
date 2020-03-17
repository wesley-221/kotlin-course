package com.example.task2.adapters

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.*
import com.example.task2.models.Game
import kotlinx.android.synthetic.main.game_overview.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_overview, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            setImageByRoll(game.userPick, itemView.ivYou)
            setImageByRoll(game.computerPick, itemView.ivComputer)

            // Tried implementing it through getString(R.strings.win_message), but couldn't figure out how to actually make it work
            when(game.gameStatus) {
                WIN_STATE -> itemView.tvGameStatus.text = "You win!"
                LOSE_STATE -> itemView.tvGameStatus.text = "Computer wins!"
                TIE_STATE -> itemView.tvGameStatus.text = "Draw"
            }

            itemView.tvDate.text = game.datePlayed.toString()
        }
    }

    /**
     * Get a random image for the computer
     */
    private fun setImageByRoll(roll: Int, image: ImageView) {
        when(roll) {
            ROCK -> {
                image.setImageResource(R.drawable.rock)
            }
            PAPER -> {
                image.setImageResource(R.drawable.paper)
            }
            SCISSOR -> {
                image.setImageResource(R.drawable.scissors)
            }
        }
    }

    /**
     * Get the win state of the user
     */
    private fun getWinState(yourPick: Int, computerPick: Int): Int {
        if(yourPick == computerPick) {
            return TIE_STATE
        }

        if(yourPick == ROCK && computerPick != PAPER) {
            return WIN_STATE
        }
        else if(yourPick == PAPER && computerPick != SCISSOR) {
            return WIN_STATE
        }
        else if(yourPick == SCISSOR && computerPick != ROCK) {
            return WIN_STATE
        }

        return LOSE_STATE
    }
}