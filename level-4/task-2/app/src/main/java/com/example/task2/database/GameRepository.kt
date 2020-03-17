package com.example.task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.task2.models.Game

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()
    suspend fun insertGame(game: Game) = gameDao.insertGame(game)
    suspend fun deleteAllGames() = gameDao.deleteAllGames()
//    suspend fun getWinCount(): Unit = gameDao.getWinCount()
//    suspend fun getDrawCount(): Unit = gameDao.getDrawCount()
//    suspend fun getLoseCount(): Unit = gameDao.getLoseCount()
}