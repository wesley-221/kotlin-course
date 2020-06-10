package com.example.level_5_task_1.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.level_5_task_1.database.NoteRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository = NoteRepository(application.applicationContext)
    val note = noteRepository.getNotepad()
}