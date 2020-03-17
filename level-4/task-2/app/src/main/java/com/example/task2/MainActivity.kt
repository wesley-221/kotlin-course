package com.example.task2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.task2.database.GameRepository
import com.example.task2.models.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val ROCK = 1
const val PAPER = 2
const val SCISSOR = 3

const val WIN_STATE = 1
const val LOSE_STATE = 2
const val TIE_STATE = 3

class MainActivity : AppCompatActivity() {
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val games = arrayListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ivRock.setOnClickListener { clickWeapon(ROCK) }
        ivPaper.setOnClickListener { clickWeapon(PAPER) }
        ivScissor.setOnClickListener { clickWeapon(SCISSOR) }

        gameRepository = GameRepository(this)

        initViews()
    }

    private fun initViews() {
        getAllGames()
    }

    /**
     * This get's called when you click one of the images at the bottom of the screen
     */
    private fun clickWeapon(weapon: Int) {
        when(weapon) {
            ROCK -> ivYouPicked.setImageResource(R.drawable.rock)
            PAPER -> ivYouPicked.setImageResource(R.drawable.paper)
            SCISSOR -> ivYouPicked.setImageResource(R.drawable.scissors)
        }

        val computerPicked = getRandomImage()

        when(getWinState(weapon, computerPicked)) {
            WIN_STATE -> tvWinMessage.text = getString(R.string.win_message, "win")
            LOSE_STATE -> tvWinMessage.text = getString(R.string.win_message, "lose")
            TIE_STATE -> tvWinMessage.text = getString(R.string.tie_message)
        }

        mainScope.launch {
            val game = Game(datePlayed = Date(), userPick = weapon, computerPick = computerPicked, gameStatus = getWinState(weapon, computerPicked))

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
                getAllGames()
            }
        }
    }

    /**
     * Get a random image for the computer
     */
    private fun getRandomImage(): Int {
        when((1 .. 3).random()) {
            ROCK -> {
                ivComputerPicked.setImageResource(R.drawable.rock)
                return ROCK
            }
            PAPER -> {
                ivComputerPicked.setImageResource(R.drawable.paper)
                return PAPER
            }
            SCISSOR -> {
                ivComputerPicked.setImageResource(R.drawable.scissors)
                return SCISSOR
            }
        }

        return -1
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

    private fun getAllGames() {
        mainScope.launch {
            val gameList = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }

            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(gameList)

            // Calculate the wins, loses and ties
            val wins = games.stream().filter { g -> g.gameStatus == WIN_STATE }.count().toString()
            val loses = games.stream().filter { g -> g.gameStatus == LOSE_STATE }.count().toString()
            val tie = games.stream().filter { g -> g.gameStatus == TIE_STATE }.count().toString()

            tvStatistics.text = getString(R.string.win_draw_lose_string, wins, loses, tie)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_show_history -> {
                val intent = Intent(this, ActivityHistory::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
