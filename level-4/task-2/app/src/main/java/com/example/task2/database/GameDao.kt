package com.example.task2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task2.LOSE_STATE
import com.example.task2.TIE_STATE
import com.example.task2.WIN_STATE
import com.example.task2.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM game_table ORDER BY datePlayed DESC")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()

    // Getting the COUNT() to work was unsuccessful, it kept saying the data wasn't being initialized
    // to the appropriate variables, made a workaround; see MainActivity::getAllGames()
//    @Query("SELECT COUNT(gameStatus) AS winCount FROM game_table WHERE gameStatus = $WIN_STATE")
//    suspend fun getWinCount()
//
//    @Query("SELECT COUNT(gameStatus) AS tieCount FROM game_table WHERE gameStatus = $TIE_STATE")
//    suspend fun getDrawCount()
//
//    @Query("SELECT COUNT(gameStatus) AS loseCount FROM game_table WHERE gameStatus = $LOSE_STATE")
//    suspend fun getLoseCount()
}