package com.example.level1_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        btnSubmit.setOnClickListener { submit() }
    }

    private fun submit() {
        var correctAnswers = 0;

        if(textFirst.text.toString().toUpperCase(Locale.ROOT) == "T") {
            correctAnswers ++
        }

        if(textSecond.text.toString().toUpperCase(Locale.ROOT) == "F") {
            correctAnswers ++;
        }

        if(textThird.text.toString().toUpperCase(Locale.ROOT) == "T") {
            correctAnswers ++;
        }

        if(textFourth.text.toString().toUpperCase(Locale.ROOT) == "F") {
            correctAnswers ++;
        }

        Toast.makeText(this@MainActivity, getString(R.string.toast_message, correctAnswers), Toast.LENGTH_SHORT).show()
    }
}
