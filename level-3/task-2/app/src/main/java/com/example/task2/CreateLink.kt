package com.example.task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task2.models.Link
import kotlinx.android.synthetic.main.content_create_link.*

const val NEW_LINK = "NEW_LINK"

class CreateLink : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_create_link)

        btnSave.setOnClickListener { onSaveClick() }
    }

    fun onSaveClick() {
        if(tvTitle.text.toString().isBlank()) {
            Toast.makeText(this, "The title can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(tvUrl.text.toString().isBlank()) {
            Toast.makeText(this, "The url can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val link = Link(tvUrl.text.toString(), tvTitle.text.toString())

        val intent = Intent()
        intent.putExtra(NEW_LINK, link)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}
