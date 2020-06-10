package com.example.task_5_level_2.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
data class Game(
    var title: String,
    var platform: String,
    var releaseDate: Date,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
) : Parcelable