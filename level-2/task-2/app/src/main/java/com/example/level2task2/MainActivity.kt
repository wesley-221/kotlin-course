package com.example.level2task2

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.level2task2.models.Question
import com.example.level2task2.models.QuestionAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    private val SWIPE_RIGHT = 8
    private val SWIPE_LEFT = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        rcView.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rcView.adapter = questionAdapter
        rcView.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        createItemTouchHelper().attachToRecyclerView(rcView)

        for(i in Question.QUESTIONS) {
            questions.add(i)
        }

        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val selectedItem = questions[position]

                if(selectedItem.answer) {
                    if(direction == SWIPE_LEFT) {
                        Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@MainActivity, "Correct!", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    if(direction == SWIPE_LEFT) {
                        Toast.makeText(this@MainActivity, "Correct!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@MainActivity, "Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }

                questions.removeAt(position)
                questionAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}
