package com.example.task_5_level_2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.task_5_level_2.models.Game

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGames(): LiveData<List<Game>> {
        return gameDao.getGames()
    }

    suspend fun insertGame(game: Game) {
        return gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        return gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        return gameDao.deleteAllGames()
    }
}