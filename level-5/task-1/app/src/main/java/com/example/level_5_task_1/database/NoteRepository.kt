package com.example.level_5_task_1.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.level_5_task_1.model.Note

class NoteRepository(context: Context) {
    private val noteDao: NoteDao

    init {
        val database = NotepadRoomDatabase.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotepad(): LiveData<Note?> {
        return noteDao.getNotepad()
    }

    suspend fun updateNotepad(note: Note) {
        noteDao.updateNote(note)
    }
}