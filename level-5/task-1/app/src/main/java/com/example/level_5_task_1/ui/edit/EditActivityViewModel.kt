package com.example.level_5_task_1.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.level_5_task_1.database.NoteRepository
import com.example.level_5_task_1.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository = NoteRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val note = MutableLiveData<Note?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean?>()

    fun updateNote() {
        if (isNoteValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNotepad(note.value!!)
                }

                success.value = true
            }
        }
    }

    private fun isNoteValid(): Boolean {
        return when {
            note.value == null -> {
                error.value = "Note must not be null"
                false
            }
            note.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }

            else -> true
        }
    }
}