package com.example.task_5_level_2.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5_level_2.R
import com.example.task_5_level_2.adapter.GameAdapter
import com.example.task_5_level_2.models.Game
import com.example.task_5_level_2.ui.addgame.AddGameActivity
import com.example.task_5_level_2.ui.addgame.CREATE_GAME
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_GAME_CODE = 100

class MainActivity : AppCompatActivity() {
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var viewModel: MainActivityViewModel
    private val savedGamesList = arrayListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

        fab.setOnClickListener {
            startAddActivity()
        }
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddGameActivity::class.java)
        startActivityForResult(intent, ADD_GAME_CODE)
    }

    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.games.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)

            gameAdapter.notifyDataSetChanged()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_CODE -> {
                    val game = data!!.getParcelableExtra<Game>(CREATE_GAME)
                    viewModel.insertGame(game)
                }

                else -> super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val selectedItem = games[position]

                viewModel.deleteGame(selectedItem)

                Snackbar.make(coordinatorLayout, "Successfully deleted game", Snackbar.LENGTH_LONG).setAction("UNDO") { viewModel.insertGame(selectedItem) }.show()
            }
        }

        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                savedGamesList.clear()
                savedGamesList.addAll(games)

                viewModel.deleteAllGames()

                Snackbar.make(coordinatorLayout, "Successfully deleted all games", Snackbar.LENGTH_LONG).setAction("UNDO") {
                    savedGamesList.forEach {
                        viewModel.insertGame(it)
                    }
                }.show()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}