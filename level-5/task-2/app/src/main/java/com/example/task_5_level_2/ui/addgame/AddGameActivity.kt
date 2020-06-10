package com.example.task_5_level_2.ui.addgame

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.task_5_level_2.R
import com.example.task_5_level_2.models.Game
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import java.util.*

const val CREATE_GAME ="CREATE_GAME"

class AddGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            onSaveClick()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onSaveClick() {
        if (etTitle.text.toString().isBlank() || etPlatform.text.toString().isBlank() || etDay.text.toString().isBlank() || etMonth.text.toString().isBlank() || etYear.text.toString().isBlank()) {
            Toast.makeText(this,"You have to fill in all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val cal = Calendar.getInstance()
        cal.set(etYear.text.toString().toInt(), etMonth.text.toString().toInt(), etDay.text.toString().toInt())

        val game = Game(etTitle.text.toString(), etPlatform.text.toString(), cal.time)

        val resultIntent = Intent()
        resultIntent.putExtra(CREATE_GAME, game)
        setResult(Activity.RESULT_OK, resultIntent)

        finish()
    }
}