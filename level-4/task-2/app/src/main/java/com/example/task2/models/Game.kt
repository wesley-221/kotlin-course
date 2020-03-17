package com.example.task2.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "game_table")
data class Game (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var datePlayed: Date,
    var userPick: Int,
    var computerPick: Int,
    var gameStatus: Int

//    @ColumnInfo(name = "winCount")
//    @Ignore
//    var winCount: Int,
//    @ColumnInfo(name = "loseCount")
//    @Ignore
//    var loseCount: Int,
//    @ColumnInfo(name = "tieCount")
//    @Ignore
//    var tieCount: Int
): Parcelable